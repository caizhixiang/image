package com.caizhixiang.springboot.service.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author caizhixiang
 * @description:
 * @Date 2019-04-26 19:39
 * @Version 1.0
 **/
@Data
public class ImageDTO {

        private Integer id;

        private Integer parentId;


        private String name;

        private String description;

        private String link;


        private Integer position;


        private Integer sort;


        private Date gmtCreate;


        private String url;


}
