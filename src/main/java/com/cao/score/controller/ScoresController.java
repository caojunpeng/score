package com.cao.score.controller;

import com.cao.score.entity.Scores;
import com.cao.score.service.ScoresService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 成绩表(Scores)表控制层
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
@RestController
@RequestMapping("scores")
public class ScoresController {
    /**
     * 服务对象
     */
    @Resource
    private ScoresService scoresService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Scores selectOne(Long id) {
        return this.scoresService.queryById(id);
    }

}