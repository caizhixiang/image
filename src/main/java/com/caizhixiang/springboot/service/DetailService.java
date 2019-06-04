package com.caizhixiang.springboot.service;

import com.caizhixiang.springboot.mapper.entity.Detail;

/**
 * @author caizhixiang
 * @description:
 * @Date 2019-06-05 0:38
 * @Version 1.0
 **/

public interface DetailService {
    void saveOrUpdate(Detail detail);

    Detail findById(Integer id);
}
