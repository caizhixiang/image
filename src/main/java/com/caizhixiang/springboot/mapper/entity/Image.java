package com.caizhixiang.springboot.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * çˆ¶å›¾ç‰‡id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * å›¾ç‰‡å��ç§°
     */
    private String name;

    /**
     * å›¾ç‰‡è¯´æ˜Ž
     */
    private String description;

    /**
     * å›¾ç‰‡é“¾æŽ¥åœ°å�€
     */
    private String link;

    /**
     * æ‰€å¤„ä½�ç½®ï¼ˆ1-ä¸»é¡µå›¾ç‰‡ï¼Œ2-è¯¦ç»†å›¾ç‰‡ï¼‰
     */
    private Integer position;

    /**
     * æŽ’åº�
     */
    private Integer sort;

    /**
     * åˆ›å»ºæ—¶é—´
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * å›¾ç‰‡åœ°å�€
     */
    private byte[] url;

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
     * 获取çˆ¶å›¾ç‰‡id
     *
     * @return parent_id - çˆ¶å›¾ç‰‡id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置çˆ¶å›¾ç‰‡id
     *
     * @param parentId çˆ¶å›¾ç‰‡id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取å›¾ç‰‡å��ç§°
     *
     * @return name - å›¾ç‰‡å��ç§°
     */
    public String getName() {
        return name;
    }

    /**
     * 设置å›¾ç‰‡å��ç§°
     *
     * @param name å›¾ç‰‡å��ç§°
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取å›¾ç‰‡è¯´æ˜Ž
     *
     * @return description - å›¾ç‰‡è¯´æ˜Ž
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置å›¾ç‰‡è¯´æ˜Ž
     *
     * @param description å›¾ç‰‡è¯´æ˜Ž
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取å›¾ç‰‡é“¾æŽ¥åœ°å�€
     *
     * @return link - å›¾ç‰‡é“¾æŽ¥åœ°å�€
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置å›¾ç‰‡é“¾æŽ¥åœ°å�€
     *
     * @param link å›¾ç‰‡é“¾æŽ¥åœ°å�€
     */
    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    /**
     * 获取æ‰€å¤„ä½�ç½®ï¼ˆ1-ä¸»é¡µå›¾ç‰‡ï¼Œ2-è¯¦ç»†å›¾ç‰‡ï¼‰
     *
     * @return position - æ‰€å¤„ä½�ç½®ï¼ˆ1-ä¸»é¡µå›¾ç‰‡ï¼Œ2-è¯¦ç»†å›¾ç‰‡ï¼‰
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 设置æ‰€å¤„ä½�ç½®ï¼ˆ1-ä¸»é¡µå›¾ç‰‡ï¼Œ2-è¯¦ç»†å›¾ç‰‡ï¼‰
     *
     * @param position æ‰€å¤„ä½�ç½®ï¼ˆ1-ä¸»é¡µå›¾ç‰‡ï¼Œ2-è¯¦ç»†å›¾ç‰‡ï¼‰
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 获取æŽ’åº�
     *
     * @return sort - æŽ’åº�
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置æŽ’åº�
     *
     * @param sort æŽ’åº�
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取åˆ›å»ºæ—¶é—´
     *
     * @return gmt_create - åˆ›å»ºæ—¶é—´
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置åˆ›å»ºæ—¶é—´
     *
     * @param gmtCreate åˆ›å»ºæ—¶é—´
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取å›¾ç‰‡åœ°å�€
     *
     * @return url - å›¾ç‰‡åœ°å�€
     */
    public byte[] getUrl() {
        return url;
    }

    /**
     * 设置å›¾ç‰‡åœ°å�€
     *
     * @param url å›¾ç‰‡åœ°å�€
     */
    public void setUrl(byte[] url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", link=").append(link);
        sb.append(", position=").append(position);
        sb.append(", sort=").append(sort);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}