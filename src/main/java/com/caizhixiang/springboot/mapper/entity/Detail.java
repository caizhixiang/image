package com.caizhixiang.springboot.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Detail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * é¦–é¡µå›¾ç‰‡id
     */
    @Column(name = "image_id")
    private Integer imageId;

    /**
     * ä»£ç �
     */
    private String code;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取é¦–é¡µå›¾ç‰‡id
     *
     * @return image_id - é¦–é¡µå›¾ç‰‡id
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * 设置é¦–é¡µå›¾ç‰‡id
     *
     * @param imageId é¦–é¡µå›¾ç‰‡id
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * 获取ä»£ç �
     *
     * @return code - ä»£ç �
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置ä»£ç �
     *
     * @param code ä»£ç �
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imageId=").append(imageId);
        sb.append(", code=").append(code);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append("]");
        return sb.toString();
    }
}