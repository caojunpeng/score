package com.cao.score.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CommonFilesService {

    public XSSFWorkbook exportExcel(String fileName, Map<String, Object> map, String sheetName);

    public void downloadLocalFile(HttpServletResponse response, String fileName);

}
