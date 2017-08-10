package com.kee.ad.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * Created by Kee on 2016/10/31.
 */
public class ExcelUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static HSSFWorkbook createExcel(String sheetName, String[] titles, String[] keys, List<Map<String, Object>> data) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);

        HSSFCellStyle cs1 = workbook.createCellStyle();
        HSSFFont f1 = workbook.createFont();
        f1.setFontHeightInPoints((short) 10);
        f1.setColor(IndexedColors.BLACK.getIndex());
        f1.setBoldweight(Font.BOLDWEIGHT_BOLD);

        cs1.setFont(f1);
        cs1.setFillForegroundColor(HSSFColor.YELLOW.index);
        cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cs1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cs1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cs1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cs1.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle cs2 = workbook.createCellStyle();
        HSSFFont f2 = workbook.createFont();
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        cs2.setFont(f2);
        cs2.setFillForegroundColor(HSSFColor.WHITE.index);
        cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //创建第一行
        HSSFRow row = sheet.createRow((short) 0);
        //设置列名
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cs1);
        }

        //设置每行每列的值
        for (int i = 1; i <= data.size(); i++) {
            HSSFRow row1 = sheet.createRow((short) i);
            for (int j = 0; j < keys.length; j++) {
                HSSFCell cell = row1.createCell(j);
                cell.setCellValue(data.get(i - 1).get(keys[j]) == null ? "" : data.get(i - 1).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return workbook;
    }


    public static List<List<String>> readExcel(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> result = new ArrayList<>();
        Workbook workbook = null;
        try {
            if (isExcel2003) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
            //get first sheet;
            Sheet sheet = workbook.getSheetAt(0);
            //get totalRows ;
            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCells =0 ;
            if (totalRows >=1 && sheet.getRow(0) !=null){
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }
            DecimalFormat df = new DecimalFormat("#");
            for (int i = 1 ; i < totalRows;i++){
                Row row = sheet.getRow(i);
                if (null == row){
                    continue;
                }
                List<String> rowList = new ArrayList<>();
                for (int j = 0; j < totalCells;j++){
                    Cell cell = row.getCell(j);
                    if (null == cell){
                        continue;
                    }
                    String cellValue = "";
                    switch (cell.getCellType()){
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cellValue = df.format(cell.getNumericCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cellValue = cell.getBooleanCellValue()+"";
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                    rowList.add(cellValue);
                }
                result.add(rowList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("*********Occur Exception when readExcel,{}",e);
        }
        return result;
    }
}
