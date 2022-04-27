package com.cao.score.entity;

import java.io.Serializable;

/**
 * (RoleMenu)实体类
 *
 * @author makejava
 * @since 2022-01-11 17:47:19
 */
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -64385276807995005L;

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

    public RoleMenu() {
    }

    public RoleMenu(Long id, Integer roleId, Integer menuId) {
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
    }
}