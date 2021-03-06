package com.cao.score.service;

import com.cao.score.entity.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * (RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 17:47:19
 */
public interface RoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoleMenu queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoleMenu> queryAllByLimit(int offset, int limit);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoleMenu> queryByMap(Map<String,Object> map);

    /**
     * 新增数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    RoleMenu insert(RoleMenu roleMenu);

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    RoleMenu update(RoleMenu roleMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    /**
     * 通过角色Id删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteByRoleId(Long id);

}