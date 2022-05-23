package com.cao.score.service;

import com.cao.score.entity.ScoreRanking;

import java.util.List;
import java.util.Map;

/**
 * (ScoreRanking)表服务接口
 *
 * @author makejava
 * @since 2022-04-28 09:46:39
 */
public interface ScoreRankingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ScoreRanking queryById(Long id);

    /**
     * 通过Map查询
     *
     * @param id 主键
     * @return 实例对象
     */
    List<ScoreRanking> queryByMap(Map<String,Object> map);

    /**
     * 新增数据
     *
     * @param scoreRanking 实例对象
     * @return 实例对象
     */
    ScoreRanking insert(ScoreRanking scoreRanking);

    /**
     * 修改数据
     *
     * @param scoreRanking 实例对象
     * @return 实例对象
     */
    ScoreRanking update(ScoreRanking scoreRanking);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteTable();

}
