package com.cao.score.service.impl;

import com.cao.score.dao.UserDao;
import com.cao.score.entity.Students;
import com.cao.score.entity.Teachers;
import com.cao.score.entity.User;
import com.cao.score.entity.UserRole;
import com.cao.score.service.UserRoleService;
import com.cao.score.service.UserService;
import com.cao.score.shiro.SaltUtil;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 17:47:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * 默认给学生权限
     */
    public static final Integer studentRole = 3;
    /**
     * 默认给教师权限
     */
    public static final Integer teacherRole = 2;

    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleService userRoleService;

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
        if(!users.isEmpty()){
            for (User user : users) {
                user.setCreateTimeStr(ScoreDateUtils.dateToStr(user.getCreateTime(),ScoreDateUtils.format_date));
            }
        }
        DataTablesResult<User> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(users);
        dataTablesResult.setRecordsFiltered(users.size());
        dataTablesResult.setRecordsTotal(users.size());
        return dataTablesResult;
    }

    @Override
    public User setUserPossword(String possword, User user,boolean type) {
        if(type){
            user.setOriginalPassword(possword);
        }
        //1.获取随机盐
        String salt = SaltUtil.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5 = new Md5Hash(possword,salt,1024);
        user.setUserPwd(md5.toHex());
        return user;
    }
    @Override
    public void saveUserByTeachers(Teachers teachers) {
        User user = userDao.queryOne(new User(teachers.getIdentityNum()));
        if(user!=null){
            setUserBYTeacher(user,teachers);
            userDao.update(user);
        }else{
            user=new User();
            setUserBYTeacher(user,teachers);
            userDao.insert(user);
            UserRole userRole = new UserRole();
            userRole.setUserId(Integer.valueOf(user.getUserId()+""));
            userRole.setRoleId(teacherRole);
            userRoleService.insert(userRole);
        }
    }
    @Override
    public void saveUserByStudent(Students students) {
        User user = userDao.queryOne(new User(students.getStudentId()));
        if(user!=null){
            setUserBYStudent(user,students);
            userDao.update(user);
        }else{
            user=new User();
            setUserBYStudent(user,students);
            userDao.insert(user);
            UserRole userRole = new UserRole();
            userRole.setUserId(Integer.valueOf(user.getUserId()+""));
            userRole.setRoleId(studentRole);
            userRoleService.insert(userRole);
        }
    }
    private void setUserBYStudent(User user,Students students){
        String salt = SaltUtil.getSalt(8);
        user.setSalt(salt);
        String identityNum = students.getIdentityNum();
        String pwd=identityNum.substring(identityNum.length()-6,identityNum.length());
        Md5Hash md5 = new Md5Hash(pwd,salt,1024);
        user.setUserPwd(md5.toHex());
        if(user.getUserId()==null){
            user.setUserName(students.getStudentId());
            user.setCreateTime(new Date());
        }
        user.setName(students.getName());
        user.setOriginalPassword(pwd);
        user.setStatus(1);
        Object object = SecurityUtils.getSubject().getPrincipal();
        user.setCreator(String.valueOf(object));
    }
    private void setUserBYTeacher(User user,Teachers teachers){
        String salt = SaltUtil.getSalt(8);
        user.setSalt(salt);
        String identityNum = teachers.getIdentityNum();
        String pwd=identityNum.substring(identityNum.length()-6,identityNum.length());
        Md5Hash md5 = new Md5Hash(pwd,salt,1024);
        user.setUserPwd(md5.toHex());
        if(user.getUserId()==null){
            user.setUserName(teachers.getIdentityNum());
            user.setCreateTime(new Date());
        }
        user.setName(teachers.getName());
        user.setPhone(teachers.getPhone());
        user.setOriginalPassword(pwd);
        user.setStatus(1);
        Object object = SecurityUtils.getSubject().getPrincipal();
        user.setCreator(String.valueOf(object));
    }

    public static void main(String[] args) {
        String str="123456789";
        System.out.println(str.substring(str.length()-6,str.length()));
    }
}