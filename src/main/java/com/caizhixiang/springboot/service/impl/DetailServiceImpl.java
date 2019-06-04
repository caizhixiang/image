package com.caizhixiang.springboot.service.impl;

import com.caizhixiang.springboot.mapper.DetailMapper;
import com.caizhixiang.springboot.mapper.entity.Detail;
import com.caizhixiang.springboot.service.DetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author caizhixiang
 * @description:
 * @Date 2019-06-05 0:42
 * @Version 1.0
 **/
@Service
@Slf4j
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailMapper detailMapper;
    @Override
    public void saveOrUpdate(Detail detail) {
        if (detail.getId() == null) {
            detail.setGmtCreate(new Date());
            detailMapper.insertSelective(detail);
        }else {
            detailMapper.updateByPrimaryKeySelective(detail);
        }
    }

    @Override
    public Detail findById(Integer id) {
        Detail detail = new Detail();
        detail.setImageId(id);
        return detailMapper.selectOne(detail);
    }
}
