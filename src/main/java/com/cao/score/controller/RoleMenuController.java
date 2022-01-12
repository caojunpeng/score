package com.cao.score.controller;

import com.cao.score.entity.RoleMenu;
import com.cao.score.service.RoleMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (RoleMenu)表控制层
 *
 * @author makejava
 * @since 2022-01-11 17:47:19
 */
@RestController
@RequestMapping("roleMenu")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleMenu selectOne(Integer id) {
        return this.roleMenuService.queryById(id);
    }

}