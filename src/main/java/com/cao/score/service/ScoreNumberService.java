package com.cao.score.service;

import com.cao.score.entity.ScoreNumber;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;

import java.util.List;

/**
 * 考试场次(ScoreNumber)表服务接口
 *
 * @author makejava
 * @since 2022-04-11 10:45:31
 */
public interface ScoreNumberService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ScoreNumber queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ScoreNumber> queryAllByLimit(int offset, int limit);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param scoreNumber 实例对象
     * @return 对象列表
     */
    List<ScoreNumber> queryAll(ScoreNumber scoreNumber);
    /**
     * 新增数据
     *
     * @param scoreNumber 实例对象
     * @return 实例对象
     */
    ScoreNumber insert(ScoreNumber scoreNumber);

    /**
     * 修改数据
     *
     * @param scoreNumber 实例对象
     * @return 实例对象
     */
    ScoreNumber update(ScoreNumber scoreNumber);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    DataTablesResult<ScoreNumber>  scoreNumberDatas(ObjectParams objectParams);

}