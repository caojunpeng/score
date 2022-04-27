package com.cao.score.service;

import com.cao.score.entity.Teachers;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;

import java.util.List;
import java.util.Map;

/**
 * 教师信息表(Teachers)表服务接口
 *
 * @author makejava
 * @since 2022-01-28 10:36:02
 */
public interface TeachersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teachers queryById(Long id);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teachers queryByMap(Map<String,Object> map);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Teachers> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param teachers 实例对象
     * @return 实例对象
     */
    Teachers insert(Teachers teachers);

    /**
     * 修改数据
     *
     * @param teachers 实例对象
     * @return 实例对象
     */
    Teachers update(Teachers teachers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    DataTablesResult<Teachers> getTeacherInfoDatas(ObjectParams objectParams);

}