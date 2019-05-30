package com.caizhixiang.springboot.service.util;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageUtilTest {

    @Test
    public void saveMinPhoto() throws IOException {
        /*C:\Users\Administrator\AppData\Local\Temp\tomcat.3281458089482666041.8080\work\Tomcat\localhost\image*/
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        String srcFile = com.getPath() + "\\微信图片_1.jpg";
        String targetFile = com.getPath() + "\\1.jpg";
        Thumbnails.of(srcFile).scale(1).toFile(targetFile);

    }
}