package com.cao.score.service;

import com.cao.score.entity.Students;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;

import java.util.List;

/**
 * 学生信息表(Students)表服务接口
 *
 * @author makejava
 * @since 2022-01-28 10:35:52
 */
public interface StudentsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Students queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Students> queryAllByLimit(int offset, int limit);

    /**
     * 通过objectParams获取学生信息对象
     * @param objectParams
     * @return
     */
    DataTablesResult<Students> getStudentInfoDatas(ObjectParams objectParams);

    /**
     * 通过ObjectParams参数查询对象
     * @param objectParams
     * @return
     */
    List<Students> getStudentByParams(ObjectParams objectParams);

    /**
     * 新增数据
     *
     * @param students 实例对象
     * @return 实例对象
     */
    boolean insert(Students students);

    /**
     * 修改数据
     *
     * @param students 实例对象
     * @return 实例对象
     */
    Students update(Students students);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}