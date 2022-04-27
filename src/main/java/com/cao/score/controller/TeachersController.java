package com.cao.score.controller;

import com.cao.score.entity.Dict;
import com.cao.score.entity.GradeClass;
import com.cao.score.entity.Students;
import com.cao.score.entity.Teachers;
import com.cao.score.service.*;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师信息表(Teachers)表控制层
 *
 * @author makejava
 * @since 2022-01-28 10:36:02
 */
@RestController
@RequestMapping("teachers")
public class TeachersController {


    private static Logger logger = LoggerFactory.getLogger(TeachersController.class);

    /**
     * 服务对象
     */
    @Resource
    private TeachersService teachersService;
    @Resource
    private GradeClassService gradeClassService;
    @Resource
    private DictService dictService;
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Teachers selectOne(Long id) {
        return this.teachersService.queryById(id);
    }

    /**
     * 跳转到教师信息管理界面
     * @return
     */
    @RequestMapping("/teacherInfoManagement")
    public ModelAndView studentInfoManagement(){
        Integer role=userRoleService.selectRolesByUserName();
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        //获取所有科目
        map.clear();
        map.put("typeId",1);
        List<Dict> dicts = dictService.queryAllDict(map);
        modelAndView.addObject("dicts",dicts);
        modelAndView.addObject("role",role);
        Object object = SecurityUtils.getSubject().getPrincipal();
        modelAndView.addObject("identityNum",object);
        modelAndView.setViewName("/teacher/teacherInfoManagement");
        return modelAndView;
    }
    /**
     * 跳转到教师信息管理界面
     * @return
     */
    @RequestMapping("/editTeacherInfoPage")
    public ModelAndView editTeacherInfoPage(Long teacherId){
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        //当前修改对象
        Teachers teachers = teachersService.queryById(teacherId);
        modelAndView.addObject("teacher",teachers);
        //获取所有科目
        map.clear();
        map.put("typeId",1);
        List<Dict> dicts = dictService.queryAllDict(map);
        modelAndView.addObject("dicts",dicts);
        modelAndView.setViewName("/teacher/editTeacherInfo");
        return modelAndView;
    }
    /**
     * 跳转到教师信息管理界面
     * @return
     */
    @RequestMapping("/addTeacherInfoPage")
    public ModelAndView addTeacherInfoPage(){
        ModelAndView modelAndView=new ModelAndView();
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        //获取所有科目
        map.clear();
        map.put("typeId",1);
        List<Dict> dicts = dictService.queryAllDict(map);
        modelAndView.addObject("dicts",dicts);
        modelAndView.setViewName("/teacher/addTeacherInfo");
        return modelAndView;
    }


    /**
     * 获取学生列表信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/teacherInfoDatas")
    public DataTablesResult<Teachers> teacherInfoDatas(ObjectParams objectParams){
        return teachersService.getTeacherInfoDatas(objectParams);
    }

    /**
     * 单个教师信息录入
     * @param teachers
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveTeacherInfo")
    public String saveTeacherInfo(Teachers teachers){
        String result = "";
        try {
            if(teachers.getId()!=null) {
                teachersService.update(teachers);
                userService.saveUserByTeachers(teachers);
                result= ResponseUtil.printJson("修改成功",teachers);
            }else{
                teachersService.insert(teachers);
                userService.saveUserByTeachers(teachers);
                result=ResponseUtil.printJson("导入成功",teachers);
            }
        }catch (Exception e){
            logger.error("教师信息录入异常，异常信息"+e.getMessage(),e);
            result=ResponseUtil.printJson("教师信息录入异常",null);
        }
        return result;
    }


    /**
     * 单个教师信息删除
     * @param id 主键id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delTeacherInfo")
    public String delTeacherInfo(Long id){
        String result = "";
        try {
            teachersService.deleteById(id);
            result=ResponseUtil.printJson("删除成功",null);
        }catch (Exception e){
            logger.error("教师信息删除异常，异常信息"+e.getMessage(),e);
            result=ResponseUtil.printJson("教师信息删除异常",null);
        }
        return result;
    }
}