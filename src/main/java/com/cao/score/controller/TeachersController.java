package com.cao.score.controller;

import com.cao.score.entity.Teachers;
import com.cao.score.service.TeachersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 教师信息表(Teachers)表控制层
 *
 * @author makejava
 * @since 2022-01-28 10:36:02
 */
@RestController
@RequestMapping("teachers")
public class TeachersController {
    /**
     * 服务对象
     */
    @Resource
    private TeachersService teachersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Teachers selectOne(Long id) {
        return this.teachersService.queryById(id);
    }

}