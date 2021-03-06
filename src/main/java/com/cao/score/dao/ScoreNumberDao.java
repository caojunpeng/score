package com.cao.score.dao;

import com.cao.score.entity.ScoreNumber;
import com.cao.score.vo.ObjectParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试场次(ScoreNumber)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-11 10:45:31
 */
@Mapper
public interface ScoreNumberDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ScoreNumber queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ScoreNumber> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


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
     * @return 影响行数
     */
    int insert(ScoreNumber scoreNumber);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ScoreNumber> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ScoreNumber> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ScoreNumber> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ScoreNumber> entities);

    /**
     * 修改数据
     *
     * @param scoreNumber 实例对象
     * @return 影响行数
     */
    int update(ScoreNumber scoreNumber);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<ScoreNumber> getScoresNumberDatas(ObjectParams objectParams);

}