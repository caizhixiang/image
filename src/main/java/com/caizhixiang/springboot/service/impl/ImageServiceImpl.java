package com.caizhixiang.springboot.service.impl;

import com.caizhixiang.springboot.mapper.ImageMapper;
import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.DTO.ImageDTO;
import com.caizhixiang.springboot.service.ImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.Date;
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

    @Override
    public PageInfo<Image> findPage(Integer position, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Image image = new Image();
        image.setPosition(position);
        List<Image> list = mapper.select(image);
        return new PageInfo<>(list);
    }

    @Override
    public void saveOrUpdate(Image image) {
        if (image.getId() == null) {
            image.setGmtCreate(new Date());
            mapper.insertSelective(image);
        } else {
            mapper.updateByPrimaryKeySelective(image);
        }
    }

    @Override
    public void remove(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public ImageDTO findById(Integer id) {
        ImageDTO dto = new ImageDTO();
        Image image = mapper.selectByPrimaryKey(id);
        if (image != null) {
            BeanUtils.copyProperties(image, dto);
            byte[] url = image.getUrl();
            if (ArrayUtils.isNotEmpty(url)) {
                BASE64Encoder encoder = new BASE64Encoder();
                String encode = encoder.encode(url);
                dto.setUrl("data:image/jpeg;base64,"+encode);
            }
        }
        return dto;
    }

}
