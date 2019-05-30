package com.caizhixiang.springboot.ftp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FtpClientTest {
    @Autowired
    private FtpClient ftpClient;
    @Test
    public void deleteFile() throws IOException {
        /*ftp://root@101.132.76.209/images/1.jpg*/
        String url = "http://www.zhiab.com/images/1.jpg";
        url = url.substring(url.lastIndexOf("/") + 1);
        boolean b = ftpClient.deleteFile(url);
        log.info("" + b);
    }
}