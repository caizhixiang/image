package com.caizhixiang.springboot.service;

import com.caizhixiang.springboot.mapper.entity.Dict;

import java.util.List;

/**
 * @author caizhixiang
 * @description: 字典
 * @Date 2019-05-24 14:03
 * @Version 1.0
 **/

public interface DictService {
    List<Dict> findByCategory(Byte category);
}
