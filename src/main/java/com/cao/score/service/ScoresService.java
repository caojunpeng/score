package com.cao.score.service;

import com.cao.score.entity.Scores;

import java.util.List;

/**
 * 成绩表(Scores)表服务接口
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
public interface ScoresService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Scores queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Scores> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param scores 实例对象
     * @return 实例对象
     */
    Scores insert(Scores scores);

    /**
     * 修改数据
     *
     * @param scores 实例对象
     * @return 实例对象
     */
    Scores update(Scores scores);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}