package com.cao.score.service.impl;

import com.cao.score.dao.UserDao;
import com.cao.score.entity.User;
import com.cao.score.service.UserService;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 17:47:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long userId) {
        return this.userDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }
    /**
     * 查询多条数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User queryOne(User user) {
        return this.userDao.queryOne(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public DataTablesResult<User> dataLists(ObjectParams params) {
        List<User> users=userDao.getList(params);
        DataTablesResult<User> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(users);
        dataTablesResult.setRecordsFiltered(users.size());
        dataTablesResult.setRecordsTotal(users.size());
        return dataTablesResult;
    }
}