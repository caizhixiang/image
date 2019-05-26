package com.caizhixiang.springboot.service.impl;

import com.caizhixiang.springboot.mapper.ImageMapper;
import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.ImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(Image.class);
        example.createCriteria().andEqualTo("position", position);
        example.setOrderByClause("position Asc,sort ASC");

        List<Image> list = mapper.selectByExample(example);
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
    public Image findById(Integer id) {
        return mapper.selectByPrimaryKey(id);

    }

}
