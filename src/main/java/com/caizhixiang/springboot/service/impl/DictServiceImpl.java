package com.caizhixiang.springboot.service.impl;

import com.caizhixiang.springboot.mapper.DictMapper;
import com.caizhixiang.springboot.mapper.entity.Dict;
import com.caizhixiang.springboot.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caizhixiang
 * @description:
 * @Date 2019-05-24 14:05
 * @Version 1.0
 **/
@Service
@Slf4j
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Dict> findByCategory(Byte category) {
        Dict dict = new Dict();
        dict.setCategory(category);
        return dictMapper.select(dict);
    }

}
