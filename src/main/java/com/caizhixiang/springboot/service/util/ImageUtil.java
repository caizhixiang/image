package com.caizhixiang.springboot.service.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @author caizhixiang
 * @description: 对图片的处理工具类
 * @Date 2019-05-30 17:01
 * @Version 1.0
 **/

public class ImageUtil {
    /**
     * 等比例压缩算法：
     * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
     *
     * @param inputStream 原图
     * @throws Exception
     */
    public static InputStream saveMinPhoto(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        Thumbnails.of(inputStream).scale(1).toOutputStream(bs);
        return new ByteArrayInputStream(bs.toByteArray());
    }

    /**
     * 加上明水印
     * @param inputStream 原图
     * @throws Exception
     */
    public static void saveWaterMarkPhoto(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        BufferedImage bufferedImage = Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\logo.png")).scale(0.2).asBufferedImage();
        Thumbnails.of(inputStream).watermark(Positions.BOTTOM_RIGHT,bufferedImage,0.5f).toFile(new File("C:\\Users\\Administrator\\Desktop\\images\\zhiab\\zuopin.png"));
    }


}
