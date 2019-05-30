package com.caizhixiang.springboot.service.util;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

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


}
