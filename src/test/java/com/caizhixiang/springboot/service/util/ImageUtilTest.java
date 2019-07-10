package com.caizhixiang.springboot.service.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtilTest {

    @Test
    public void saveMinPhoto() throws IOException {
        /*C:\Users\Administrator\AppData\Local\Temp\tomcat.3281458089482666041.8080\work\Tomcat\localhost\image*/
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        String srcFile = com.getPath() + "\\微信图片_1.jpg";
        String targetFile = com.getPath() + "\\1.jpg";
        Thumbnails.of(srcFile).scale(1).toFile(targetFile);

    }

    /**
     * 明水印
     * @throws IOException
     */
    @Test
    public void saveWaterMarkPhoto() throws IOException {
        BufferedImage bufferedImage = Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\logo.png")).scale(0.3).asBufferedImage();
        Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\微信图片_1.jpg"))
                .watermark(Positions.BOTTOM_RIGHT, bufferedImage, 1f)
                .scale(1).toFile(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\zuopin.png"));

    }

    /**
     * 盲水印
     * @throws IOException
     */
    @Test
    public void saveBlindWaterMarkPhoto() throws IOException {
        BufferedImage bufferedImage = Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\logo.png")).scale(0.3).asBufferedImage();
        Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\微信图片_1.jpg"))
                .watermark(Positions.BOTTOM_RIGHT, bufferedImage, 1f)
                .scale(1).toFile(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\zuopin.png"));

    }


}