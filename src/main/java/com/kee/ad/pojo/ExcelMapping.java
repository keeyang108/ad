package com.kee.ad.pojo;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * Created by Kee on 2016/10/31.
 */
public class ExcelMapping {

    private String headTextName;
    private String propertyName;
    private Integer cols;
    private XSSFCellStyle cellStyle;

    public ExcelMapping(String headTextName, String propertyName) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
    }

    public ExcelMapping(String headTextName, String propertyName, Integer cols) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }

    public ExcelMapping() {
    }

    public String getHeadTextName() {
        return headTextName;
    }

    public void setHeadTextName(String headTextName) {
        this.headTextName = headTextName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public XSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}
