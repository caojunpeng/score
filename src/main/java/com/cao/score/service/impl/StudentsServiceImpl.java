package com.cao.score.service.impl;

import com.cao.score.dao.StudentsDao;
import com.cao.score.entity.Role;
import com.cao.score.entity.Students;
import com.cao.score.service.StudentsService;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生信息表(Students)表服务实现类
 *
 * @author makejava
 * @since 2022-01-28 10:35:52
 */
@Service("studentsService")
public class StudentsServiceImpl implements StudentsService {
    @Resource
    private StudentsDao studentsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Students queryById(Long id) {
        return this.studentsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Students> queryAllByLimit(int offset, int limit) {
        return this.studentsDao.queryAllByLimit(offset, limit);
    }

    @Override
    public DataTablesResult<Students> getStudentInfoDatas(ObjectParams objectParams) {
        List<Students> studentInfoDatas = studentsDao.getStudentInfoDatas(objectParams);
        DataTablesResult<Students> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(studentInfoDatas);
        dataTablesResult.setRecordsFiltered(studentInfoDatas.size());
        dataTablesResult.setRecordsTotal(studentInfoDatas.size());
        return dataTablesResult;
    }

    /**
     * 新增数据
     *
     * @param students 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Students students) {
        boolean type = false;
        Map<String,Object> map = new HashMap();
        map.put("identityNum",students.getIdentityNum());
        Students stu = studentsDao.queryByMap(map);//通过身份证号过滤重复的数据
        if(stu==null){
            type=studentsDao.insert(students)>0;
        }
        return type;
    }

    /**
     * 修改数据
     *
     * @param students 实例对象
     * @return 实例对象
     */
    @Override
    public Students update(Students students) {
        this.studentsDao.update(students);
        return this.queryById(students.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.studentsDao.deleteById(id) > 0;
    }
}