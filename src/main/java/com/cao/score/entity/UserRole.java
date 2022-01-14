package com.cao.score.entity;

import java.io.Serializable;

/**
 * 用户权限(UserRole)实体类
 *
 * @author makejava
 * @since 2022-01-11 17:47:46
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 176689037030744680L;

    private Long id;

    private Integer roleId;

    private Integer menuId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}