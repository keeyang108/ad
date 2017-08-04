package com.kee.ad.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * Created by Kee on 2016/10/31.
 */
public class ExcelUtil {

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
}
