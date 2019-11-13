package com.github.soysaucelm.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.time.LocalDate;
import java.util.List;

/**
 * @author SoySauce
 * @since 2019/5/15
 */
public class CommentPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        topLevelClass.addJavaDocLine("/**");
        sb.append(" *");
        sb.append(" @author ");
        sb.append("SoySauce");
        sb.append("\r\n");
        sb.append(" * @since ");
        sb.append(LocalDate.now());
        sb.append("\r\n");
        sb.append(" * @version 1.0");
        sb.append("\r\n");
        sb.append(" */");
        topLevelClass.addJavaDocLine(sb.toString());
        return true;
    }
}