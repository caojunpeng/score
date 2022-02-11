package com.cao.score.service.impl;

import com.cao.score.service.CommonFilesService;
import com.cao.score.utiles.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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





    /**
     * 导出指定格式的Excel
     * @param fileName
     * @param map
     * @param sheetName
     * @return
     */
    @Override
    public XSSFWorkbook exportExcel(String fileName, Map<String, Object> map, String sheetName) {
        XSSFWorkbook wb = null;
        String mC=null;
        List<List<String>> selectAllByParm = new ArrayList<>();
        List<String> titlList = new ArrayList<>();
        if ("通讯录".equals(sheetName)) {
            String[] title = { "学号", "姓名","性别", "年龄", "出生日期", "身份证号", "家庭住址", "年纪", "班级" };// 标题
            titlList = Arrays.asList(title);
            //selectAllByParm = contacts(map.get("param"));
        }
        wb = ExcelUtils.getXSSFExportExcel(sheetName, titlList, selectAllByParm, mC, new XSSFWorkbook());
        return wb;
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




}
