package com.cao.score.service.impl;

import com.cao.score.dao.ScoresDao;
import com.cao.score.dao.StudentsDao;
import com.cao.score.entity.Role;
import com.cao.score.entity.Students;
import com.cao.score.entity.Teachers;
import com.cao.score.entity.User;
import com.cao.score.service.ScoresService;
import com.cao.score.service.StudentsService;
import com.cao.score.service.TeachersService;
import com.cao.score.service.UserRoleService;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.utiles.ScoreStringUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private TeachersService teachersService;
    @Resource
    private ScoresDao scoresDao;

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

        if(!objectParams.isExportType()) {
            PageHelper.offsetPage(objectParams.getStart(), objectParams.getLength());
        }
        List<Students> studentInfoDatas = studentsDao.getStudentInfoDatas(objectParams);
        if(!studentInfoDatas.isEmpty()){
            for(Students student:studentInfoDatas){
                String birthDateStr = "";
                Date birthdate = student.getBirthdate();
                if(birthdate!=null){
                    birthDateStr = ScoreDateUtils.dateToStr(birthdate,ScoreDateUtils.format_date);
                }
                student.setBirthDateStr(birthDateStr);
                editBtnStatue(student);
            }
        }
        PageInfo<Students> page = new PageInfo<Students>(studentInfoDatas);
        DataTablesResult<Students> dataTablesResult=new DataTablesResult<>();
        dataTablesResult.setData(page.getList());
        dataTablesResult.setRecordsFiltered(page.getTotal());
        dataTablesResult.setRecordsTotal(page.getTotal());
        return dataTablesResult;
    }
    //学生管理操作按钮状态
    private void editBtnStatue(Students student){
        Integer role=userRoleService.selectRolesByUserName();
        if(role==3){
            student.setEditStatue(false);
        }else if(role==2){
            Object object = SecurityUtils.getSubject().getPrincipal();
            Map<String,Object> map = new HashMap<>();
            map.put("identityNum",object);
            Teachers teachers = teachersService.queryByMap(map);
            if(teachers!=null){
                Integer classNum = teachers.getClassNum();
                Integer gradeNum = teachers.getGradeNum();
                if(teachers.getClassNum()==student.getClassNum()&&teachers.getGradeNum()==student.getGradeNum()){
                    student.setEditStatue(true);
                }
            }
        }else if(role==1){
            student.setEditStatue(true);
        }
    }

    @Override
    public List<Students> getStudentByParams(ObjectParams objectParams) {
        return studentsDao.getStudentInfoDatas(objectParams);
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

    /**
     * 成绩排名任务
     */
    @Override
    public void scoreRanking(){
        //获取所有考试场次
        List<String> scoresNumList = scoresDao.getAllScoresNum();
        //将一次考试作为条件，查出所有学生各科成绩
        if(scoresNumList.isEmpty()){
            for (String s : scoresNumList) {
                
            }
        }
        //计算每位学生的总成绩，录入学生表
        //通过年级和考试场次，进行总分排序，结果放在年级排名
        //通过年级和班级和考试场次，进行总分拍讯，结果放在班级排名
    }
}