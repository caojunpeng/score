package com.cao.score.dao;

import com.cao.score.entity.Teachers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教师信息表(Teachers)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-28 10:36:02
 */
@Mapper
public interface TeachersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teachers queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Teachers> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param teachers 实例对象
     * @return 对象列表
     */
    List<Teachers> queryAll(Teachers teachers);

    /**
     * 新增数据
     *
     * @param teachers 实例对象
     * @return 影响行数
     */
    int insert(Teachers teachers);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Teachers> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Teachers> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Teachers> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Teachers> entities);

    /**
     * 修改数据
     *
     * @param teachers 实例对象
     * @return 影响行数
     */
    int update(Teachers teachers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}