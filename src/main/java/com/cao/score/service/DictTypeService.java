package com.cao.score.service;

import com.cao.score.entity.DictType;

import java.util.List;

/**
 * (DictType)表服务接口
 *
 * @author makejava
 * @since 2022-01-20 15:08:42
 */
public interface DictTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DictType queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DictType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    DictType insert(DictType dictType);

    /**
     * 修改数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    DictType update(DictType dictType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}