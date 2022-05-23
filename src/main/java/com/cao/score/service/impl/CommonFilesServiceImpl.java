package com.cao.score.service.impl;

import com.cao.score.entity.Students;
import com.cao.score.service.CommonFilesService;
import com.cao.score.service.ScoresService;
import com.cao.score.service.StudentsService;
import com.cao.score.utiles.ExcelUtils;
import com.cao.score.utiles.ScoreDateUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ScoreParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CommonFilesServiceImpl implements CommonFilesService {

    private static Logger logger = LoggerFactory.getLogger(CommonFilesServiceImpl.class);

    @Resource
    private StudentsService studentsService;
    @Resource
    private ScoresService scoresService;

    /**
     * 导出指定格式的Excel
     * @param fileName
     * @param map
     * @param sheetName
     * @return
     */
    @Override
    public void exportExcel(String fileName, Map<String, Object> map, String sheetName, HttpServletRequest request, HttpServletResponse response) {
        XSSFWorkbook wb = null;
        String mC=null;
        List<List<String>> selectAllByParm = new ArrayList<>();
        List<String> titlList = new ArrayList<>();
        if ("学生基本信息".equals(sheetName)) {
            String[] title = { "学号", "姓名","性别", "年龄", "出生日期", "身份证号", "家庭住址", "年纪", "班级" };// 标题
            titlList = Arrays.asList(title);
            selectAllByParm = studentInfoSheet((ObjectParams)map.get("param"));
        }else if("成绩信息导出".equals(sheetName)){
            String[] title = { "年级", "班级","学号", "姓名", "语文", "数学", "英语", "政治", "历史" , "地理" , "生物" , "物理" , "化学" , "总成绩" , "班级排名" , "年级排名" };// 标题
            titlList = Arrays.asList(title);
            selectAllByParm = scoresInfoSheet((ObjectParams)map.get("param"));
        }
        wb = ExcelUtils.getXSSFExportExcel(sheetName, titlList, selectAllByParm, mC, new XSSFWorkbook());
        downFile(response, fileName, wb, request);
    }
    private List<List<String>> studentInfoSheet(ObjectParams param) {
        List<Students> Studentslist = new ArrayList<>();
        List<List<String>> selectAllByParm = new ArrayList<>();
        DataTablesResult<Students> studentInfoDatas = studentsService.getStudentInfoDatas(param);
        if(studentInfoDatas!=null && !studentInfoDatas.getData().isEmpty()){
            Studentslist = studentInfoDatas.getData();
        }
        for(Students students:Studentslist) {
            List<String> excel = new ArrayList<>();
            excel.add(students.getStudentId());
            excel.add(students.getName());
            Integer sex = students.getSex();
            String sexStr = "";
            if(sex==1){
                sexStr = "男";
            }else if(sex==2){
                sexStr = "女";
            }else{
                sexStr = "未知";
            }
            excel.add(sexStr);
            excel.add(students.getAge()+"");
            excel.add(students.getBirthDateStr());
            excel.add(students.getIdentityNum());
            excel.add(students.getAddress());
            excel.add(students.getGradeNum()+"");
            excel.add(students.getClassNum()+"");
            selectAllByParm.add(excel);
        }
        return selectAllByParm;
    }
    private List<List<String>> scoresInfoSheet(ObjectParams param) {
        List<ScoreParams> scoreslist = new ArrayList<>();
        List<List<String>> selectAllByParm = new ArrayList<>();
        DataTablesResult<ScoreParams> scoresInfoDatas = scoresService.getScoresInfoDatas(param);
        if(scoresInfoDatas!=null && !scoresInfoDatas.getData().isEmpty()){
            scoreslist = scoresInfoDatas.getData();
        }
        for(ScoreParams scoreParam:scoreslist) {
            List<String> excel = new ArrayList<>();
            excel.add(scoreParam.getGradeNum()+"");
            excel.add(scoreParam.getClassNum()+"");
            excel.add(scoreParam.getStudentId());
            excel.add(scoreParam.getName());
            excel.add(scoreParam.getChineseScore()+"");
            excel.add(scoreParam.getMathScore()+"");
            excel.add(scoreParam.getEnglishScore()+"");
            excel.add(scoreParam.getPoliticsScore()+"");
            excel.add(scoreParam.getHistoryScore()+"");
            excel.add(scoreParam.getGeographyScore()+"");
            excel.add(scoreParam.getBiologicalScore()+"");
            excel.add(scoreParam.getPhysicalScore()+"");
            excel.add(scoreParam.getChemicalScore()+"");
            excel.add(scoreParam.getScoreSum()+"");
            excel.add(scoreParam.getClassRanking()+"");
            excel.add(scoreParam.getGradeRanking()+"");
            selectAllByParm.add(excel);
        }
        return selectAllByParm;
    }


    /**
     * 下载内置文件
     * @param response
     * @param fileName 文件名称
     */
    @Override
    public void downloadLocalFile(HttpServletResponse response, String fileName) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("static/template/" + fileName);
        /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
        response.setContentType("multipart/form-data");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = stream.read(b)) > 0) {
                os.write(b, 0, len);
            }
            os.flush();
            os.close();
            stream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    // 将文件存到指定位置
    private void downFile(HttpServletResponse response, String fileName, XSSFWorkbook wb, HttpServletRequest request) {
        try {
            this.setResponseHeader(response, request, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 公用方法
    public void setResponseHeader(HttpServletResponse response,HttpServletRequest request, String fileName) {
        try {
            response.reset();
            String userAgent = request.getHeader("User-Agent");
            byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
            fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName)); // 文件名外的双引号处理firefox的空格截断问题
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
