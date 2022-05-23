package com.cao.score.service.impl;

import com.cao.score.dao.GradeClassDao;
import com.cao.score.dao.ScoresDao;
import com.cao.score.dao.StudentsDao;
import com.cao.score.entity.*;
import com.cao.score.service.*;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.utiles.ScoreStringUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(StudentsServiceImpl.class);

    @Resource
    private StudentsDao studentsDao;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private TeachersService teachersService;
    @Resource
    private ScoreNumberService scoreNumberService;
    @Resource
    private GradeClassDao GradeClassDao;
    @Resource
    private ScoresDao scoresDao;
    @Resource
    private ScoreRankingService scoreRankingService;

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
                    birthDateStr = ScoreDateUtils.dateToStr(birthdate,ScoreDateUtils.format_day);
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
        Thread thread = new Thread(() -> {
            logger.info("排名任务开始");
            scoreRankingService.deleteTable();
            //计算总成绩
            totalScore();
            //排名
            rankingScore();
            logger.info("排名任务结束");
        });
        thread.start();
    }

    //计算并录入总出成绩
    public void totalScore(){
        try {
            //获取所有考试场次
            List<ScoreNumber> scoreNumbers=scoreNumberService.queryAll(null);
            if(!scoreNumbers.isEmpty()){
                for (ScoreNumber scoreNumber : scoreNumbers) {
                    ObjectParams objectParams = new ObjectParams();
                    objectParams.setScoreNum(scoreNumber.getScoreNum());
                    List<ScoreParams> scoreDatas = scoresDao.getScoresInfoDatas(objectParams);
                    if(!scoreDatas.isEmpty()){
                        for (ScoreParams scoreData : scoreDatas) {
                            double allScore=scoreData.getBiologicalScore()+scoreData.getChemicalScore()+scoreData.getChineseScore()+scoreData.getEnglishScore()
                                    +scoreData.getMathScore()+scoreData.getGeographyScore()+scoreData.getHistoryScore()+scoreData.getPhysicalScore()
                                    +scoreData.getPoliticsScore();
                            scoreData.setScoreSum(allScore);
                        }
                        for (ScoreParams scoreData : scoreDatas) {
                            ScoreRanking scoreRanking = new ScoreRanking();
                            scoreRanking.setScoreSum(scoreData.getScoreSum());
                            scoreRanking.setStudentId(scoreData.getStudentId());
                            scoreRanking.setScoreNum(scoreNumber.getScoreNum());
                            scoreRanking.setClassNum(scoreData.getClassNum());
                            scoreRanking.setGradeNum(scoreData.getGradeNum());
                            scoreRankingService.insert(scoreRanking);
                        }
                    }
                }
            }

        }catch (Exception e){
            logger.error("排名任务-总成绩录入失败"+e.getMessage(),e);
        }

    }

    //进行排名并进行录入排名
    public void rankingScore(){
        try {
            //获取所有考试场次
            List<ScoreNumber> scoreNumbers=scoreNumberService.queryAll(null);
            //获取所有的年级
            Map<String,Object> map = new HashMap<>();
            map.put("groupStr","grade_num");
            List<GradeClass> gradeNumList = GradeClassDao.queryAllByMap(map);
            //将一次考试作为条件，查出所有学生各科成绩
            if(!scoreNumbers.isEmpty()){
                for (ScoreNumber sn : scoreNumbers) {
                    if(!gradeNumList.isEmpty()){
                        for (GradeClass gradeClass : gradeNumList) {
                            //年级排名
                            map.clear();
                            map.put("gradeNum",gradeClass.getGradeNum());
                            map.put("scoreNum",sn.getScoreNum());
                            List<ScoreRanking> scoreRankingList = scoreRankingService.queryByMap(map);
                            setRankingInfo(scoreRankingList,1);
                            //班级排名
                            map.put("groupStr","class_num");
                            map.put("gradeNum",gradeClass.getGradeNum());
                            List<GradeClass> classList = GradeClassDao.queryAllByMap(map);
                            if(!classList.isEmpty()){
                                for (GradeClass aClass : classList) {
                                    map.clear();
                                    map.put("gradeNum",gradeClass.getGradeNum());
                                    map.put("scoreNum",sn.getScoreNum());
                                    map.put("classNum",aClass.getClassNum());
                                    List<ScoreRanking> scoreClassRankingList = scoreRankingService.queryByMap(map);
                                    setRankingInfo(scoreClassRankingList,2);
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("排名任务-排名失败"+e.getMessage(),e);
        }

    }

    /**
     * 排名字段赋值
     * @param scoreRankingList 排名对象
     * @param type  1-年级；2-班级；
     */
    private void setRankingInfo(List<ScoreRanking> scoreRankingList,Integer type){
        try{
            if(!scoreRankingList.isEmpty()){
                for (int i=0;i<scoreRankingList.size();i++) {
                    ScoreRanking one = scoreRankingList.get(i);
                    if(type==1){
                        one.setGradeRanking(i+1);
                    }else{
                        one.setClassRanking(i+1);
                    }
                    scoreRankingService.update(one);
                }
            }
        }catch (Exception e){
            logger.error("排名任务-排名名次信息录入失败"+e.getMessage(),e);
        }
    }
}