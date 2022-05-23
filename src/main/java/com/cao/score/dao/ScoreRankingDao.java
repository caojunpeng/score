package com.cao.score.dao;

import com.cao.score.entity.ScoreRanking;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (ScoreRanking)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-28 09:46:39
 */
public interface ScoreRankingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ScoreRanking queryById(Long id);


    /**
     * 统计总行数
     *
     * @param scoreRanking 查询条件
     * @return 总行数
     */
    long count(ScoreRanking scoreRanking);

    /**
     * 新增数据
     *
     * @param scoreRanking 实例对象
     * @return 影响行数
     */
    int insert(ScoreRanking scoreRanking);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ScoreRanking> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ScoreRanking> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ScoreRanking> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ScoreRanking> entities);

    /**
     * 修改数据
     *
     * @param scoreRanking 实例对象
     * @return 影响行数
     */
    int update(ScoreRanking scoreRanking);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteTable();

    List<ScoreRanking> queryByMap(Map<String, Object> map);

}

