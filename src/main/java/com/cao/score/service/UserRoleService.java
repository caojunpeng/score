package com.cao.score.service;

import com.cao.score.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * 用户权限(UserRole)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 17:47:46
 */
public interface UserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserRole queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole userRole);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole userRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    void editUserRoles(String[] roleIds, Long userId);
    List<UserRole> queryAllByMap(Map<String,Object> map);

    /**
     * 通过用户ID查询所有授权信息
     * @param id
     * @return
     */
    List<String> selectUserRoles(Long id);

    /**
     * 通过用户名获取最大权限
     * @return
     */
    Integer selectRolesByUserName();
}