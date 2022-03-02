package com.cao.score.controller;

import com.cao.score.entity.Dict;
import com.cao.score.entity.GradeClass;
import com.cao.score.entity.Students;
import com.cao.score.service.CommonFilesService;
import com.cao.score.service.DictService;
import com.cao.score.service.GradeClassService;
import com.cao.score.service.StudentsService;
import com.cao.score.utiles.*;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * 学生信息表(Students)表控制层
 *
 * @author makejava
 * @since 2022-01-28 10:35:52
 */
@RestController
@RequestMapping("students")
public class StudentsController {

    private static Logger logger = LoggerFactory.getLogger(StudentsController.class);

    /**
     * 安装目录
     */
    @Value("#{T(java.lang.System).getProperty('user.dir')}")
    private String installDir;

    /**
     * 服务对象
     */
    @Resource
    private StudentsService studentsService;

    @Resource
    private GradeClassService gradeClassService;

    @Resource
    private CommonFilesService commonFilesService;

    @Resource
    private DictService dictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Students selectOne(Long id) {
        return this.studentsService.queryById(id);
    }

    /**
     * 跳转到学生信息录入
     * @return
     */
    @RequestMapping("/studentInfoEnter")
    public ModelAndView studentEnter(){
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        modelAndView.setViewName("/student/studentEnter");
        return modelAndView;
    }

    /**
     * 跳转到学生信息管理界面
     * @return
     */
    @RequestMapping("/studentInfoManagement")
    public ModelAndView studentInfoManagement(){
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        modelAndView.setViewName("/student/studentInfoManagement");
        return modelAndView;
    }

    /**
     * 获取学生列表信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/studentInfoDatas")
    public DataTablesResult<Students> studentInfoDatas(ObjectParams objectParams){
        return studentsService.getStudentInfoDatas(objectParams);
    }
    /**
     * 获取所属年级的所有班级
     * @return
     */
    @ResponseBody
    @RequestMapping("/getClassNumList")
    public String getClassNumList(ObjectParams objectParams){
        String result="";
        try{
            //获取所有的年级
            Map<String,Object> map = new HashMap<>();
            map.put("gradeNum",objectParams.getGradeNum()+"");
            map.put("groupStr","class_num");
            List<GradeClass> classNumList = gradeClassService.queryAllByMap(map);
            result=ResponseUtil.printJson("查询成功",classNumList);
        }catch (Exception e){
            logger.error("获取所属年级的所有班级异常，异常信息："+e.getMessage(),e);
            result=ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"获取所属年级的所有班级异常");
        }

        return result;
    }

    /**
     * 导入学生信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportStudentsInfo")
    public String exportStudentsInfo(@RequestParam("files") MultipartFile files){
        String result = "";
        try {
            String dirPath = installDir + File.separator + "studentinfoexcels" ;
            File file = new File(dirPath);
            if(!file.exists()){
                ScoreFileUtil.forceMkdir(file);
            }
            String filePath=dirPath + File.separator + files.getOriginalFilename();
            ScoreFileUtil.multipartFileToFile(files,filePath);//保存到本地
            List<Map<String, Object>> maps = ExcelUtils.readExcelToMaps(files);
            if (!maps.isEmpty()){
                for(Map<String, Object> map:maps){
                    Students students = new Students();
                    students.setStudentId(map.get("firstColumn")+"");
                    students.setName(map.get("secondColumn")+"");
                    students.setSex(Integer.valueOf(map.get("thirdlyColumn")+""));
                    students.setAge(Integer.valueOf(map.get("fourthlyColumn")+""));
                    String birthdatae= map.get("fifthColumn")+"";
                    Date date=ScoreDateUtils.parseStrToDate(birthdatae,ScoreDateUtils.format_day);
                    students.setBirthdate(date);
                    students.setIdentityNum(map.get("sixthColumn")+"");
                    students.setAddress(map.get("seventhColumn")+"");
                    students.setGradeNum(Integer.valueOf(map.get("eighthColumn")+""));
                    students.setClassNum(Integer.valueOf(map.get("ninthColumn")+""));
                    studentsService.insert(students);
                }
                result=ResponseUtil.printJson("导入成功",maps);
            }else{
                result=ResponseUtil.printJson("文件内容为空",maps);
            }
        }catch (Exception e){
            logger.error("导入学生信息异常,异常信息:"+e.getMessage(),e);
            result=ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"文件导入异常");
        }
        return result;
    }

    /**
     * 单个学生信息录入
     * @param students
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveStudentInfo")
    public String saveStudentInfo(Students students){
        String result = "";
        try {
            if(students.getId()!=null) {
                studentsService.update(students);
                result=ResponseUtil.printJson("修改成功",students);
            }else{
                studentsService.insert(students);
                result=ResponseUtil.printJson("导入成功",students);
            }
        }catch (Exception e){
            logger.error("学生信息录入异常，异常信息"+e.getMessage(),e);
            result=ResponseUtil.printJson("学生信息录入异常",null);
        }
        return result;
    }

    /**
     * 单个学生信息录入
     * @param id 主键id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delStudentInfo")
    public String delStudentInfo(Long id){
        String result = "";
        try {
            studentsService.deleteById(id);
            result=ResponseUtil.printJson("删除成功",null);
        }catch (Exception e){
            logger.error("学生信息录入异常，异常信息"+e.getMessage(),e);
            result=ResponseUtil.printJson("学生信息录入异常",null);
        }
        return result;
    }


    /**
     * 跳转到修改或新增页面
     * @param studentId
     * @return
     */
    @RequestMapping("/saveStudentInfoPage")
    public ModelAndView saveStudentInfoPage(Long studentId){
        ModelAndView modelAndView=new ModelAndView();
        if (studentId != null){
            Students students = studentsService.queryById(studentId);
            modelAndView.addObject("student",students);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        modelAndView.setViewName("/student/saveStudentInfo");
        return modelAndView;
    }

    /**
     * 学生excel模板下载
     * @param response
     * @return
     */
    @RequestMapping("/downloadStudentExcelTemplate")
    public void downloadStudentExcelTemplate(HttpServletResponse response){
        String fileName = "studentInfoTemplate.xlsx";
        commonFilesService.downloadLocalFile(response,fileName);
    }

}