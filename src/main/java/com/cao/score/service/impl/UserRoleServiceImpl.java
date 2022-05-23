package com.cao.score.service.impl;

import com.cao.score.dao.UserDao;
import com.cao.score.dao.UserRoleDao;
import com.cao.score.entity.User;
import com.cao.score.entity.UserRole;
import com.cao.score.service.UserRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户权限(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 17:47:46
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserRole queryById(Long id) {
        return this.userRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryAllByLimit(int offset, int limit) {
        return this.userRoleDao.queryAllByLimit(offset, limit);
    }
    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryAllByMap(Map<String,Object> map) {
        return this.userRoleDao.queryAllByMap(map);
    }

    @Override
    public List<String> selectUserRoles(Long id) {
        List<String> roleIds = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",id);
        List<UserRole> userRoles=userRoleDao.queryAllByMap(map);
        if(!userRoles.isEmpty()){
            for (UserRole userRole : userRoles) {
                roleIds.add(userRole.getRoleId()+"");
            }
        }
        return roleIds;
    }

    @Override
    public Integer selectRolesByUserName() {
        Object object = SecurityUtils.getSubject().getPrincipal();
        List<Integer> roleIds = new ArrayList<>();
        User user = userDao.queryOne(new User(String.valueOf(object)));
        Map<String,Object> map = new HashMap<>();
        map.put("userId",user.getUserId());
        List<UserRole> userRoles=userRoleDao.queryAllByMap(map);
        if(!userRoles.isEmpty()){
            for (UserRole userRole : userRoles) {
                roleIds.add(userRole.getRoleId());
            }
        }
        Integer result = 0;
        if(!roleIds.isEmpty()){
            if(roleIds.contains(1)){
                result=1;
            }else if(roleIds.contains(2)){
                result=2;
            }else if(roleIds.contains(3)){
                result=3;
            }
        }
        return result;
    }

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole userRole) {
        this.userRoleDao.insert(userRole);
        return userRole;
    }

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole update(UserRole userRole) {
        this.userRoleDao.update(userRole);
        return this.queryById(userRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userRoleDao.deleteById(id) > 0;
    }

    /**
     * 重置用户权限
     * @param roleIds
     * @param userId
     */
    @Override
    public void editUserRoles(String[] roleIds, Long userId) {
        this.userRoleDao.deleteByUserId(userId);
        if(roleIds.length>0){
            for (String roleId : roleIds) {
                UserRole userRole = new UserRole(null,Integer.valueOf(roleId),Integer.valueOf(userId+""));
                userRoleDao.insert(userRole);
            }
        }
    }
}