package com.caizhixiang.springboot.mapper.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Dict implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * å­—å…¸ç±»åˆ«å��
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * ç±»åˆ«ï¼ˆ1-å›¾ç‰‡ä½�ç½®ï¼‰
     */
    private Byte category;

    /**
     * è¯´æ˜Ž
     */
    private String description;

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
     * 获取å­—å…¸ç±»åˆ«å��
     *
     * @return dict_name - å­—å…¸ç±»åˆ«å��
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置å­—å…¸ç±»åˆ«å��
     *
     * @param dictName å­—å…¸ç±»åˆ«å��
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * 获取ç±»åˆ«ï¼ˆ1-å›¾ç‰‡ä½�ç½®ï¼‰
     *
     * @return category - ç±»åˆ«ï¼ˆ1-å›¾ç‰‡ä½�ç½®ï¼‰
     */
    public Byte getCategory() {
        return category;
    }

    /**
     * 设置ç±»åˆ«ï¼ˆ1-å›¾ç‰‡ä½�ç½®ï¼‰
     *
     * @param category ç±»åˆ«ï¼ˆ1-å›¾ç‰‡ä½�ç½®ï¼‰
     */
    public void setCategory(Byte category) {
        this.category = category;
    }

    /**
     * 获取è¯´æ˜Ž
     *
     * @return description - è¯´æ˜Ž
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置è¯´æ˜Ž
     *
     * @param description è¯´æ˜Ž
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dictName=").append(dictName);
        sb.append(", category=").append(category);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}