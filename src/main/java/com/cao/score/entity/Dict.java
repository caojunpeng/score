package com.cao.score.entity;

import java.io.Serializable;

/**
 * (Dict)实体类
 *
 * @author makejava
 * @since 2022-01-29 17:21:14
 */
public class Dict implements Serializable {
    private static final long serialVersionUID = -73677848006654235L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 字典ID
     */
    private Integer dictId;
    /**
     * 字典类型
     */
    private Integer typeId;
    /**
     * 内容信息
     */
    private String dictName;
    /**
     * 描述
     */
    private String dictMsg;
    /**
     * 状态：0-可用；1-禁用
     */
    private Integer dictStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictMsg() {
        return dictMsg;
    }

    public void setDictMsg(String dictMsg) {
        this.dictMsg = dictMsg;
    }

    public Integer getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Integer dictStatus) {
        this.dictStatus = dictStatus;
    }

}