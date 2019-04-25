package com.caizhixiang.springboot.service.impl;

import com.caizhixiang.springboot.mapper.ImageMapper;
import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caizhixiang
 * @description:
 * @Date 2019-04-25 15:51
 * @Version 1.0
 **/
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper mapper;
    @Override
    public List<Image> findAll() {
        return mapper.selectAll();
    }
}
