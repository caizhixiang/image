package com.caizhixiang.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "image.platform")
@Component
public class PropertyFtpConfig{

	private String server;
	private int port;
	private String username;
	private String password;
	private String location;
	private String urlPrefix;

}
