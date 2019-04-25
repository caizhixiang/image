package com.caizhixiang.springboot.service;

import com.caizhixiang.springboot.mapper.entity.Image;

import java.util.List;

/**
 * @author caizhixiang
 * @description:
 * @Date 2019-04-25 15:50
 * @Version 1.0
 **/

public interface ImageService {

    List<Image> findAll();
}
