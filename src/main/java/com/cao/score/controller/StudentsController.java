package com.cao.score.controller;

import com.cao.score.entity.GradeClass;
import com.cao.score.entity.Students;
import com.cao.score.service.*;
import com.cao.score.utiles.*;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserService userService;

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
        Integer role=userRoleService.selectRolesByUserName();
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        modelAndView.addObject("role",role);
        modelAndView.setViewName("/student/studentEnter");
        return modelAndView;
    }

    /**
     * 跳转到学生信息管理界面
     * @return
     */
    @RequestMapping("/studentInfoManagement")
    public ModelAndView studentInfoManagement(){
        Integer role=userRoleService.selectRolesByUserName();
        String studentId =null;
        if(role==3){
            Object object = SecurityUtils.getSubject().getPrincipal();
            studentId = String.valueOf(object);
        }
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        modelAndView.addObject("role",role);
        modelAndView.addObject("studentId",studentId);
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
     * 获取所属班级的所有学生信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/getStudentList")
    public String getStudentList(ObjectParams objectParams){
        String result="";
        try{
            //获取当前班级的所有学生信息
            List<Students> classNumList = studentsService.getStudentByParams(objectParams);
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
                    students.setStudentId(map.get("zeroColumn")+"");
                    students.setName(map.get("oneColumn")+"");
                    students.setSex(Integer.valueOf(map.get("twoColumn")+""));
                    students.setAge(Integer.valueOf(map.get("threeColumn")+""));
                    String birthdatae= map.get("fourColumn")+"";
                    Date date=ScoreDateUtils.parseStrToDate(birthdatae,ScoreDateUtils.format_day);
                    students.setBirthdate(date);
                    students.setIdentityNum(map.get("fiveColumn")+"");
                    students.setAddress(map.get("sixColumn")+"");
                    students.setGradeNum(Integer.valueOf(map.get("evenColumn")+""));
                    students.setClassNum(Integer.valueOf(map.get("eightColumn")+""));
                    studentsService.insert(students);
                    userService.saveUserByStudent(students);
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
                userService.saveUserByStudent(students);
                result=ResponseUtil.printJson("修改成功",students);
            }else{
                studentsService.insert(students);
                userService.saveUserByStudent(students);
                result=ResponseUtil.printJson("导入成功",students);

            }
        }catch (Exception e){
            logger.error("学生信息录入异常，异常信息"+e.getMessage(),e);
            result=ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"学生信息录入异常");
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
            result=ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"学生信息录入异常");
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
            students.setBirthDateStr(ScoreDateUtils.dateToStr(students.getBirthdate(),ScoreDateUtils.format_date));
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

    // 学生信息导出
    @ResponseBody
    @RequestMapping(value = "/exportStudentInfo")
    public void harvestRateReport(ObjectParams param, HttpServletRequest request, HttpServletResponse response) {
        String sheetName = "学生基本信息";
        String fileName = sheetName + ScoreDateUtils.dateToStr(new Date(), "yyyyMMddHHmmss") + ScoreFileUtil.EXCEL;
        Map<String, Object> map = new HashMap<>();
        map.put("param", param);
       commonFilesService.exportExcel(fileName, map, sheetName,  request,  response);
    }

}