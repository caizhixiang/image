package com.caizhixiang.springboot.config;

import com.caizhixiang.springboot.ftp.FtpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpConfig {

	@Bean
	@ConditionalOnMissingBean
	public FtpClient createFtpClient(PropertyFtpConfig ftpConfig) {
		FtpClient ftpClient = new FtpClient();
		ftpClient.setFtpConfig(ftpConfig);
		return ftpClient;
	}



}
