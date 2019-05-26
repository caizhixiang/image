package com.caizhixiang.springboot.ftp;

import com.caizhixiang.springboot.config.PropertyFtpConfig;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Ftp客户端
 * 
 * @author Xiongmw
 * @version 1.0.0
 */
public class FtpClient {

	private PropertyFtpConfig ftpConfig;

	public PropertyFtpConfig getFtpConfig() {
		return ftpConfig;
	}

	public void setFtpConfig(PropertyFtpConfig ftpConfig) {
		this.ftpConfig = ftpConfig;
	}

	/**
	 * 构建文件访问的Url
	 * 
	 * @param url
	 * @return
	 */
	public String buildFileUrl(String url) {
		Assert.notNull(getFtpConfig(), " param 'ftpConfig' can't be null!");
		String urlPrefix = getFtpConfig().getUrlPrefix();
		StringBuilder result = new StringBuilder();
		result.append(urlPrefix);
		if (!urlPrefix.endsWith("/") && !url.startsWith("/")) {
			result.append("/");
		}
		result.append(url);
		return result.toString();
	}

	/**
	 * 创建文件路径
	 * 
	 * @param fileName
	 *            原文件名称
	 * @param workDir
	 *            放置目录
	 * @return
	 */
	public String buildFilePath(String fileName, String workDir) {
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(System.currentTimeMillis());
		fileNameBuilder.append("_");
		fileNameBuilder.append(RandomStringUtils.random(4, false, true));
		fileNameBuilder.append(".");
		fileNameBuilder.append(FilenameUtils.getExtension(fileName));
		return buildFilePath(fileName, workDir, fileNameBuilder.toString());
	}

	/**
	 * 创建文件路径
	 * 
	 * @param fileName
	 *            原文件名称
	 * @param workDir
	 *            放置目录
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public String buildFilePath(String fileName, String workDir, String filename) {
		StringBuilder fileNameBuilder = new StringBuilder();
//		String env = Config.env();
		String env = "env";
		// 根目录(当前所在环境目录)
		fileNameBuilder.append(env + "/");
		// 自定义业务目录
		fileNameBuilder.append(workDir + "/");
		// 日期目录
		fileNameBuilder.append(DateUtil.format(new Date(), "yyyyMMdd"));
		fileNameBuilder.append("/");

		// 文件名称
		if (StringUtils.isBlank(filename)) {
			filename = System.currentTimeMillis() + "_";
			filename += RandomStringUtils.random(4, false, true);
			filename += "." + FilenameUtils.getExtension(fileName);
		}
		fileNameBuilder.append(filename);
		return fileNameBuilder.toString();
	}

	/**
	 * 创建ftp客户端
	 * 
	 * @return
	 * @throws IOException
	 */
	protected FTPClient createFTPClient() throws IOException {
		Assert.notNull(getFtpConfig(), " param 'ftpConfig' can't be null!");
		PropertyFtpConfig config = getFtpConfig();
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(config.getServer(), config.getPort());
		boolean isSuccess = ftpClient.login(config.getUsername(), config.getPassword());
		Assert.isTrue(isSuccess, "ftp login failure!");

		ftpClient.enterLocalPassiveMode();
		ftpClient.setBufferSize(1024);// 设置上传缓存大小
		ftpClient.setControlEncoding("UTF-8");// 设置编码
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);// 设置文件类型
		String path = config.getLocation();
		if (StringUtils.isNotBlank(path) && !ftpClient.changeWorkingDirectory(path)) {
			Assert.isTrue(ftpClient.makeDirectory(path), "create dir '" + path + "' error!");
			ftpClient.changeWorkingDirectory(path);
		}
		return ftpClient;
	}

	/**
	 * 关闭ftp客户端
	 * 
	 * @param ftpClient
	 * @throws IOException
	 */
	protected void closeFTPClient(FTPClient ftpClient) throws IOException {
		if (ftpClient != null && ftpClient.isConnected()) {
			ftpClient.logout();
			ftpClient.disconnect();
		}
	}

	/**
	 * 级联创建目录
	 * 
	 * @param ftpClient
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean createDir(FTPClient ftpClient, String path) throws IOException {
		if (StringUtils.isBlank(path)) {
			return true;
		}
		path = FilenameUtils.separatorsToUnix(path);
		String[] dirArrays = path.split("/");
		for (String dir : dirArrays) {
			if (ftpClient.changeWorkingDirectory(dir)) {
				continue;
			}
			Assert.isTrue(ftpClient.makeDirectory(dir), "create dir '" + dir + "' error!");
			if (!ftpClient.changeWorkingDirectory(dir)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 * @param iStream
	 * @throws IOException
	 */
	public boolean uploadFile(String fileName, InputStream iStream) throws IOException {
		FTPClient ftpClient = null;
		try {
			ftpClient = createFTPClient();
			String path = FilenameUtils.getPathNoEndSeparator(fileName);
			if (createDir(ftpClient, path)) {
				String name = FilenameUtils.getName(fileName);
				return ftpClient.storeFile(new String(name.getBytes("utf-8"),"8859_1"), iStream);
			} else {
				throw new IOException("change or make directory error!");
			}
		} finally {
			closeFTPClient(ftpClient);
			IOUtils.closeQuietly(iStream);
		}
	}
}
