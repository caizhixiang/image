package com.caizhixiang.springboot.service.enums;

/**
 * @author caizhixiang
 * @description: 字典枚举
 * @Date 2019-05-24 14:07
 * @Version 1.0
 **/

public enum  DictEnum {
    IMAGE_POSITION((byte)1,"图片位置"),
    IMAGE_CATEGORY((byte)2,"图片类别"),
    ;

    private Byte code;
    private String name;


    private DictEnum(Byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public Byte getCode() {
        return code;
    }
    public String getName() {
        return name;
    }



}
