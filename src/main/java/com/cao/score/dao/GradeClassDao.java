package com.cao.score.dao;

import com.cao.score.entity.GradeClass;
import com.cao.score.vo.ObjectParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (GradeClass)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-02 14:44:54
 */
public interface GradeClassDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GradeClass queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GradeClass> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param gradeClass 实例对象
     * @return 对象列表
     */
    List<GradeClass> queryAll(GradeClass gradeClass);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param gradeClass 实例对象
     * @return 对象列表
     */
    List<GradeClass> queryAllByMap(Map<String,Object> gradeClass);

    /**
     * 新增数据
     *
     * @param gradeClass 实例对象
     * @return 影响行数
     */
    int insert(GradeClass gradeClass);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<GradeClass> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GradeClass> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GradeClass> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<GradeClass> entities);

    /**
     * 修改数据
     *
     * @param gradeClass 实例对象
     * @return 影响行数
     */
    int update(GradeClass gradeClass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}