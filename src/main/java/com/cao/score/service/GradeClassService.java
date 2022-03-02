package com.cao.score.service;

import com.cao.score.entity.GradeClass;
import com.cao.score.vo.ObjectParams;

import java.util.List;
import java.util.Map;

/**
 * (GradeClass)表服务接口
 *
 * @author makejava
 * @since 2022-03-02 14:44:55
 */
public interface GradeClassService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GradeClass queryById(Long id);

    /**
     * 查询多个对象
     * @param gradeClass
     * @return
     */
    public List<GradeClass> queryAllByMap(Map<String,Object> gradeClass);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GradeClass> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param gradeClass 实例对象
     * @return 实例对象
     */
    GradeClass insert(GradeClass gradeClass);

    /**
     * 修改数据
     *
     * @param gradeClass 实例对象
     * @return 实例对象
     */
    GradeClass update(GradeClass gradeClass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}