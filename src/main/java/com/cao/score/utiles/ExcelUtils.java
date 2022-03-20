package com.cao.score.utiles;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelUtils {

    public static XSSFWorkbook getXSSFExportExcel(String sheetName, List<String> titlList, List<List<String>> selectAllByParm, String mergeCell1, XSSFWorkbook wb1) {
        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook wb = wb1;
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        getSheet(sheetName, titlList, selectAllByParm, mergeCell1, wb);
        return wb;
    }

    public static void getSheet(String sheetName, List<String> titlList, List<List<String>> selectAllByParm, String mergeCell1, XSSFWorkbook wb) {
        XSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < titlList.size(); i++) {
            sheet.setColumnWidth(i, 100 * 70);
        }
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        XSSFCell cell = null;
        // 创建标题
        for (int i = 0; i < titlList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titlList.get(i));
            cell.setCellStyle(style);
        }

        //导出不需要合并的单元格
        if (ScoreStringUtils.isBlank(mergeCell1)) {
            // 创建内容
            for (int i = 0; i < selectAllByParm.size(); i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < titlList.size(); j++) {
                    row.createCell(j).setCellValue(selectAllByParm.get(i).get(j));
                }
                row.setRowStyle(style);
            }
        } else {
            for (int i = 0; i < selectAllByParm.size(); i++) {
                row = sheet.createRow(i + 1);
                boolean f = false;
                for (int j = 0; j < titlList.size(); j++) {
                    try {
                        if (titlList.size() == 3 && j == 0 && ScoreStringUtils.isNotBlank(selectAllByParm.get(i).get(0)) && ScoreStringUtils.isBlank(selectAllByParm.get(i).get(1))
                                && ScoreStringUtils.isBlank(selectAllByParm.get(i).get(2))) {
                            f = true;
                        }
                        if (f) {
                            row.createCell(j).setCellValue(selectAllByParm.get(i).get(j));
                            CellRangeAddress region = new CellRangeAddress(i + 1, i + 1, 0, 2);
                            sheet.addMergedRegion(region);
                            break;
                        } else {
                            row.createCell(j).setCellValue(selectAllByParm.get(i).get(j));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                row.setRowStyle(style);
            }
        }
    }

    /**
     * 解析excel
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Map<String,Object>> readExcelToMaps(MultipartFile file) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        List<Map<String,Object>> acclist = new ArrayList<Map<String,Object>>();
        boolean isE2007 = false; // 判断是否是excel2007格式
        if (file.getOriginalFilename().endsWith("xlsx"))
            isE2007 = true;
        try {
            InputStream input = file.getInputStream(); // 建立输入流
            Workbook wb = null;

            // 根据文件格式(2003或者2007)来初始化
            if (isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i); // 获得第一个表单
                if (sheet.getSheetName().equals("Sheet1")) {
                    Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
                    while (rows.hasNext()) {
                        Row row = rows.next(); // 获得行数据
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
                        Map<String,Object> maps = new HashMap<>();
                        while (cells.hasNext()) {
                            Cell cell = cells.next();
                            if (cell.getColumnIndex() == 0 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("zeroColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 1 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("oneColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 2 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("twoColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 3 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("threeColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 4 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("fourColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 5 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("fiveColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 6 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("sixColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 7 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("evenColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 8 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("eightColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 9 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("nineColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 10 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("tenColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 11 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("elevenColumn",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 12 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("twelveColumn",cell.getStringCellValue().trim());
                            }
                        }
                        acclist.add(maps);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acclist;
    }
}
