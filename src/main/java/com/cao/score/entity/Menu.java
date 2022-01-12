package com.cao.score.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表(Menu)实体类
 *
 * @author makejava
 * @since 2022-01-11 17:46:12
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -92827764570818200L;

    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 标签
     */
    private String menuTitle;
    /**
     * 父级名称
     */
    private String parentName;
    /**
     * 请求地址
     */
    private String action;
    /**
     * 排序
     */
    private Integer orderBy;
    /**
     * 状态:1可用;0:禁用
     */
    private Integer status;
    /**
     * 图标
     */
    private String icon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 点击菜单触发函数
     */
    private String menuFunction;
    /**
     * 菜单样式名
     */
    private String menuStyle;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMenuFunction() {
        return menuFunction;
    }

    public void setMenuFunction(String menuFunction) {
        this.menuFunction = menuFunction;
    }

    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

}