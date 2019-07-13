package com.caizhixiang.springboot.service.impl;

import com.caizhixiang.springboot.ftp.FtpClient;
import com.caizhixiang.springboot.mapper.ImageMapper;
import com.caizhixiang.springboot.mapper.entity.Dict;
import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.DictService;
import com.caizhixiang.springboot.service.ImageService;
import com.caizhixiang.springboot.web.vo.ImageRes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private FtpClient ftpClient;
    @Autowired
    private DictService dictService;

    @Override
    public List<Image> findAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<ImageRes> findPage(Integer category, Integer pageNo, Integer pageSize, String order, String orderName) {
        PageInfo<ImageRes> result = new PageInfo();
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Image.class);
        example.createCriteria().andEqualTo("category", category);
        example.setOrderByClause(orderName+" "+order);

        List<Image> list = mapper.selectByExample(example);
        List<ImageRes> imageRes = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            imageRes = list.stream().filter(n -> n != null).map(image -> {
                ImageRes res = new ImageRes();
                BeanUtils.copyProperties(image, res);
                if (res.getCategory() != null) {
                    Dict dict = dictService.findById(res.getCategory());
                    if (dict != null) {
                        res.setCategoryName(dict.getDictName());
                    }
                }
                return res;
            }).collect(Collectors.toList());
        }
        result.setList(imageRes);
        result.setTotal(new PageInfo<>(list).getTotal());
        return result;
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
        Image image = mapper.selectByPrimaryKey(id);
        if (image != null) {
            mapper.deleteByPrimaryKey(id);
            String url = image.getUrl();
            if (StringUtils.isNotBlank(url)) {
                url = url.substring(url.lastIndexOf("/") + 1);
                try {
                    ftpClient.deleteFile(url);
                } catch (IOException e) {
                    log.error("删除文件失败");
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Image findById(Integer id) {
        return mapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Image> findByCategory(Integer category) {
        Image image = new Image();
        image.setCategory(category);
        return mapper.select(image);
    }

}
