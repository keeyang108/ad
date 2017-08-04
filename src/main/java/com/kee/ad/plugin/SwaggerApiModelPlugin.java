package com.kee.ad.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;

/**
 * @author KeeYang on 2017/7/21.
 * @Description :
 */
public class SwaggerApiModelPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }

    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn column, IntrospectedTable table, ModelClassType modelClassType) {

        StringBuilder annotationBuilder = new StringBuilder("@ApiModelProperty(");
        String remarks = column.getRemarks();
        annotationBuilder.append("value=\"").append(remarks.replaceAll("\"", "\\\"")).append("\"").append(!column.isIdentity() && !column.isNullable() ? ", required=true" : "");
        if (!column.getJdbcTypeName().equals("TIMESTAMP")) {
            annotationBuilder.append(StringUtility.stringHasValue(column.getDefaultValue()) ? ", example=\"" + column.getDefaultValue() + "\"" : "");
        }

        annotationBuilder.append(")");
        field.addAnnotation(annotationBuilder.toString());
        TableConfiguration tc = table.getTableConfiguration();

        return true;
    }

    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addAnnotation("@ApiModel(\"" + introspectedTable.getFullyQualifiedTable() + "\")");
        return true;
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addAnnotation("@ApiModel(\"" + introspectedTable.getFullyQualifiedTable() + "\")");
        return true;
    }

    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addAnnotation("@ApiModel(\"" + introspectedTable.getFullyQualifiedTable() + "\")");
        return true;
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }
}
