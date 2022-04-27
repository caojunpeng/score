package com.cao.score.service.impl;

import com.cao.score.dao.RoleMenuDao;
import com.cao.score.entity.Role;
import com.cao.score.entity.RoleMenu;
import com.cao.score.service.RoleMenuService;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 17:47:19
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuDao roleMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoleMenu queryById(Long id) {
        return this.roleMenuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleMenu> queryAllByLimit(int offset, int limit) {
        return this.roleMenuDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<RoleMenu> queryByMap(Map<String, Object> map) {
        return this.roleMenuDao.queryByMap(map);
    }

    /**
     * 新增数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public RoleMenu insert(RoleMenu roleMenu) {
        this.roleMenuDao.insert(roleMenu);
        return roleMenu;
    }

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public RoleMenu update(RoleMenu roleMenu) {
        this.roleMenuDao.update(roleMenu);
        return this.queryById(roleMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.roleMenuDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByRoleId(Long id) {
        return this.roleMenuDao.deleteByRoleId(id) > 0;
    }


}