package com.cao.score.dao;

import com.cao.score.entity.Scores;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成绩表(Scores)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
@Mapper
public interface ScoresDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Scores queryById(Long id);


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Scores> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param scores 实例对象
     * @return 对象列表
     */
    List<Scores> queryAll(Scores scores);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param scores 实例对象
     * @return 对象列表
     */
    Scores queryOneByScoreParams(ScoreParams scores);

    /**
     * 新增数据
     *
     * @param scores 实例对象
     * @return 影响行数
     */
    int insert(Scores scores);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Scores> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Scores> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Scores> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Scores> entities);

    /**
     * 修改数据
     *
     * @param scores 实例对象
     * @return 影响行数
     */
    int update(Scores scores);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过学号清空成绩信息
     * @param id
     * @return
     */
    int deleteByStudentId(String studentId);

    List<ScoreParams> getScoresInfoDatas(ObjectParams objectParams);

}