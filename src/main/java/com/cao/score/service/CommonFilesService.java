package com.cao.score.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CommonFilesService {

    public void exportExcel(String fileName, Map<String, Object> map, String sheetName, HttpServletRequest request, HttpServletResponse response);

    public void downloadLocalFile(HttpServletResponse response, String fileName);

}
