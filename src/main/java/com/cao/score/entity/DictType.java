package com.cao.score.entity;

import java.io.Serializable;

/**
 * (DictType)实体类
 *
 * @author makejava
 * @since 2022-01-20 15:08:42
 */
public class DictType implements Serializable {
    private static final long serialVersionUID = -91062412633184972L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 字典类型
     */
    private Integer typeId;
    /**
     * 类型名称
     */
    private String typeName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}