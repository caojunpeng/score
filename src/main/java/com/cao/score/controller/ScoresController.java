package com.cao.score.controller;

import com.cao.score.entity.*;
import com.cao.score.service.*;
import com.cao.score.utiles.ExcelUtils;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.utiles.ScoreFileUtil;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成绩表(Scores)表控制层
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
@RestController
@RequestMapping("scores")
public class ScoresController {

    private static Logger logger = LoggerFactory.getLogger(ScoresController.class);

    /**
     * 安装目录
     *
     */
    @Value("#{T(java.lang.System).getProperty('user.dir')}")
    private String installDir;
    /**
     * 服务对象
     */
    @Resource
    private ScoresService scoresService;

    @Resource
    private GradeClassService gradeClassService;

    @Resource
    private CommonFilesService commonFilesService;
    @Resource
    private ScoreNumberService scoreNumberService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private TeachersService teachersService;
    @Resource
    private StudentsService studentsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Scores selectOne(Long id) {
        return this.scoresService.queryById(id);
    }
    /**
     * 跳转到成绩信息录入
     * @return
     */
    @RequestMapping("/scoreEnter")
    public ModelAndView scoresEnter(){
        ModelAndView modelAndView=new ModelAndView();
        Integer role=userRoleService.selectRolesByUserName();
        if(role==2){
            Object object = SecurityUtils.getSubject().getPrincipal();
            Map<String,Object> map = new HashMap<>();
            map.put("identityNum",object);
            Teachers teachers = teachersService.queryByMap(map);
            if(teachers!=null){
                modelAndView.addObject("classNum",teachers.getClassNum());
                modelAndView.addObject("gradeNum",teachers.getGradeNum());
            }
        }
        //获取所有的年级
        Map<String,Object> map = new HashMap<>();
        map.put("groupStr","grade_num");
        List<GradeClass> gradeNumList = gradeClassService.queryAllByMap(map);
        modelAndView.addObject("gradeNumList",gradeNumList);
        List<ScoreNumber> scoreNumList = scoreNumberService.queryAll(null);
        modelAndView.addObject("scoreNumList",scoreNumList);
        modelAndView.addObject("role",role);
        modelAndView.setViewName("/score/scoresEnter");
        return modelAndView;
    }

    /**
     * 跳转到成绩信息管理
     * @return
     */
    @RequestMapping("/scoreManagement")
    public ModelAndView scoreManagement(String scoreNum){
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
        modelAndView.addObject("scoreNum",scoreNum);
        modelAndView.addObject("role",role);
        modelAndView.addObject("studentId",studentId);

        modelAndView.setViewName("/score/scoresManagement");
        return modelAndView;
    }

    /**
     * 跳转到成绩信息录入
     * @return
     */
    @RequestMapping("/saveScoresInfoPage")
    public ModelAndView saveScoresInfoPage(ObjectParams objectParams){
        ModelAndView modelAndView=new ModelAndView();
        List<ScoreParams> scoresInfos = scoresService.getScoresInfos(objectParams);
        ScoreParams scoreParams = new ScoreParams();
        if(!scoresInfos.isEmpty()){
            scoreParams = scoresInfos.get(0);
        }
        modelAndView.addObject("score",scoreParams);
        modelAndView.addObject("scoreNum",objectParams.getScoreNum());
        modelAndView.setViewName("/score/saveScoresInfo");
        return modelAndView;
    }


    /**
     * 获取学生成绩信息列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/scoresInfoDatas")
    public DataTablesResult<ScoreParams> scoresInfoDatas(ObjectParams objectParams){
        Integer role=userRoleService.selectRolesByUserName();
        if(role==0){//管理员可以看所有学生成绩信息
            objectParams.setStudentId("");
        }
        return scoresService.getScoresInfoDatas(objectParams);

    }

    /**
     * 导入学生信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportScoreInfo")
    public String exportScoreInfo(@RequestParam("files") MultipartFile files,String scoreNum){
        String result = "";
        try {
            String dirPath = installDir + File.separator + "scoreinfoexcels" ;
            File file = new File(dirPath);
            if(!file.exists()){
                ScoreFileUtil.forceMkdir(file);
            }
            String filePath=dirPath + File.separator + files.getOriginalFilename();
            ScoreFileUtil.multipartFileToFile(files,filePath);//保存到本地
            List<Map<String, Object>> maps = ExcelUtils.readExcelToMaps(files);
            //todo插入数据
            if(!maps.isEmpty()){
                for (Map<String, Object> map:maps) {
                    ScoreParams scoreParams = new ScoreParams();
                    scoreParams.setGradeNum(Integer.valueOf(map.get("zeroColumn")+""));
                    scoreParams.setClassNum(Integer.valueOf(map.get("oneColumn")+""));
                    scoreParams.setStudentId(map.get("twoColumn")+"");
                    scoreParams.setName(map.get("threeColumn")+"");
                    scoreParams.setChineseScore(Double.valueOf(map.get("fourColumn")+""));
                    scoreParams.setMathScore(Double.valueOf(map.get("fiveColumn")+""));
                    scoreParams.setEnglishScore(Double.valueOf(map.get("sixColumn")+""));
                    scoreParams.setPoliticsScore(Double.valueOf(map.get("evenColumn")+""));
                    scoreParams.setHistoryScore(Double.valueOf(map.get("eightColumn")+""));
                    scoreParams.setGeographyScore(Double.valueOf(map.get("nineColumn")+""));
                    scoreParams.setBiologicalScore(Double.valueOf(map.get("tenColumn")+""));
                    scoreParams.setPhysicalScore(Double.valueOf(map.get("elevenColumn")+""));
                    scoreParams.setChemicalScore(Double.valueOf(map.get("twelveColumn")+""));
                    scoreParams.setScoreNum(scoreNum);
                    scoresService.saveScoreByParams(scoreParams);
                }
            }
            studentsService.scoreRanking();
            result=ResponseUtil.printJson("成绩信息导入成功",null);
        }catch (Exception e){
            logger.error("导入学生信息异常,异常信息:"+e.getMessage(),e);
            result=ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"文件导入异常");
        }
        return result;
    }

    /**
     * 导入学生信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveScoreByParams")
    public String saveScoreByParams(ScoreParams scoreParams){
        String result = scoresService.saveScoreByParams(scoreParams);
        studentsService.scoreRanking();
        return result;
    }

    /**
     * 通过学号清空学生成绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteByStudentId")
    public boolean deleteByStudentId(ObjectParams objectParams){
        return scoresService.deleteByStudentId(objectParams.getStudentId());
    }
    /**
     * 成绩excel模板下载
     * @param response
     * @return
     */
    @RequestMapping("/downloadScoresExcelTemplate")
    public void downloadScoresExcelTemplate(HttpServletResponse response){
        String fileName = "scoresInfoTemplate.xlsx";
        commonFilesService.downloadLocalFile(response,fileName);
    }
    /**
     * 成绩信息导出
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/downloadScoresInfo")
    public void downloadScoresInfo(ObjectParams param, HttpServletRequest request, HttpServletResponse response) {
        String sheetName = "成绩信息导出";
        String fileName = sheetName + ScoreDateUtils.dateToStr(new Date(), "yyyyMMddHHmmss") + ScoreFileUtil.EXCEL;
        Map<String, Object> map = new HashMap<>();
        map.put("param", param);
        commonFilesService.exportExcel(fileName, map, sheetName,  request,  response);
        studentsService.scoreRanking();
    }


}