package com.caizhixiang.springboot.web.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author caizhixiang
 * @description: 图片列表返回实体
 * @Date 2019-07-08 23:31
 * @Version 1.0
 **/
@Data
public class ImageRes {

    private Integer id;

    private Integer parentId;


    private String name;


    private String url;


    private String description;


    private String link;

    private String thumUrl;


    private Integer position;

    private String positionName;


    private Integer category;

    private String categoryName;

    private Integer sort;

    private Date gmtCreate;

}
