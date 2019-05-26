package com.caizhixiang.springboot.ftp;

import com.caizhixiang.springboot.exception.BizException;
import com.caizhixiang.springboot.service.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FTPClientUtil {
	
	private static final String FILE_TYPE_IMAGE = "image/jpeg";
	private static final String FILE_TYPE_EXCEL = "excel";
	private static final String FILE_TYPE_WORD = "word";


	private FTPClient ftpClient;

	@Value("${image.platform.location}")
	private String remoteDirectory;

	@Value("${image.platform.host}")
	private String host;

	@Value("${image.platform.userName}")
	private String userName;

	@Value("${image.platform.password}")
	private String password;

	/**
	 * 修改linux文件权限为777
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void setLinuxFileAuthority(String filePath) throws IOException {
		if (StringUtils.equalsIgnoreCase("linux", "linux")) {
			String cmd = "chmod 777 " + filePath;
			Runtime.getRuntime().exec(cmd);
		}
	}

	public void ftpClientInit() {

		try {
			if (ftpClient == null || ftpClient.isConnected() == false) {
				this.ftpClient = new FTPClient();
				int ftpReply;
				ftpClient.connect(host);// 默认端口连接FTP服务器
				ftpClient.login(userName, password);
				ftpReply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(ftpReply)) {
					ftpClient.disconnect();
					log.info("ftp 连接失败！");
				}
				log.info("ftp 连接成功！");
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("ftp 连接异常 ");
		}
	}

	/**
	 * 递归遍历出目录下面所有文件
	 * 
	 * @param pathName
	 *            需要遍历的目录，必须以"/"开始和结束
	 * @throws IOException
	 */
	public void List(String pathName) throws IOException {
		if (pathName.startsWith("/") && pathName.endsWith("/")) {
			String directory = pathName;
			// 更换目录到当前目录
			this.ftpClient.changeWorkingDirectory(directory);
			FTPFile[] files = this.ftpClient.listFiles();
			for (FTPFile file : files) {
				if (file.isFile()) {

					log.info("list---fileName：" + file.getName());

				} else if (file.isDirectory()) {
					List(directory + file.getName() + "/");
				}
			}
		}
	}

	// ftp 上传文件
	public String uploadFile(String newFileName, InputStream inputStream) {
		String url = "";
		try {
			ftpClientInit();
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setBufferSize(1024);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.changeWorkingDirectory(remoteDirectory); 
			ftpClient.storeFile(newFileName, inputStream);
			inputStream.close();

			url = "http://" + remoteDirectory.replace("/etc/httpd/htdocs/", "") + newFileName;

		} catch (IOException e) {
			log.error("ftp 上传文件 异常 ");
			throw new BizException(ErrorCodeEnum.UPLOADFILEERROR);
		} finally {
			closeFTPConnect();
		}
		return url;
	}

	public void closeFTPConnect() {
		try {
			ftpClient.logout();
			if (this.ftpClient.isConnected()) {
				this.ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("ftp 关闭连接出现异常 ", e);
		}
	}

	// ftp 删除文件
	public void delFile(String fileName) {
		try {
			ftpClientInit();
			ftpClient.deleteFile(remoteDirectory + fileName);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("ftp 删除文件 异常 ");
		}
	}

	// ftp 下载文件 返回 InputStream
	public InputStream downloadFile(String fileName) {
		InputStream inputStream = null;
		try {
			ftpClientInit();
			ftpClient.setBufferSize(1024);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			inputStream = ftpClient.retrieveFileStream(remoteDirectory + fileName);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("ftp 下载文件 异常 ");
		}
		return inputStream;
	}

	public static String generateFileName(String fileName, String fileType) throws Exception {
		String newFileName = "";
		if (StringUtils.isEmpty(fileName)) {
			throw new BizException(StringUtils.EMPTY, "请选择文件");
		}
		if ("blob".equals(fileName)){
			fileName = "sign.jpg";
		}
		if (StringUtils.equalsIgnoreCase(FILE_TYPE_IMAGE, fileType)) {
			if (!StringUtils.endsWithIgnoreCase(fileName, ".jpg") && !StringUtils.endsWithIgnoreCase(fileName, ".png") && !StringUtils.endsWithIgnoreCase(fileName, ".gif")) {
				throw new BizException(StringUtils.EMPTY, "文件只支持jpg、png、gif格式");
			}
		}
		if (StringUtils.equalsIgnoreCase(FILE_TYPE_EXCEL, fileType)) {
			if (!StringUtils.endsWithIgnoreCase(fileName, ".xlsx")) {
				throw new BizException(StringUtils.EMPTY, "文件只支持xlsx格式");
			}
		}
		if (StringUtils.equalsIgnoreCase(FILE_TYPE_WORD, fileType)) {
			if (!StringUtils.endsWithIgnoreCase(fileName, ".doc") && !StringUtils.endsWithIgnoreCase(fileName, ".docx")) {
				throw new BizException(StringUtils.EMPTY, "文件只支持doc、docx格式");
			}
		}

		String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;

		newFileName = String.valueOf(System.currentTimeMillis()) + "-." + type;

		return newFileName;
	}

	public void setRemoteDirectory(String remoteDirectory) {
		this.remoteDirectory = remoteDirectory;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
