package mldn.jdbc.util;

import mldn.jdbc.annotation.Column;
import mldn.jdbc.annotation.Primary;
import mldn.jdbc.annotation.Table;
import mldn.jdbc.exception.NonePersistenceException;

import java.lang.reflect.Field;
import java.sql.JDBCType;

/**
 * @Auther lovely
 * @Create 2020-03-30 21:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 解析注解的工具类
 */
public class ParseUtil {
    /**
     * 根据 当前给定的实体类 进行结构的解析
     * @param clazz 当前给定实体类的Class实例
     * @return 获取完成的表名称，列信息的Bean对象
     */
    public static TableBean handler(Class<?> clazz) {
        Table tableAnnotation = clazz.getAnnotation(Table.class);//尝试 指定的annotation
        if (tableAnnotation == null) {
            throw new NonePersistenceException("没有实现持久化类标准");
        }
        //定义tableBean
        TableBean tableBean = new TableBean(tableAnnotation.value(),tableAnnotation.deleteBefore());
        //找到当前类的所有属性反射信息
        for (Field field : clazz.getDeclaredFields()) {
            //获取Column注解
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                //定义tableBean.ColumnBean类
                TableBean.ColumnBean columnBean = new TableBean.ColumnBean(columnAnnotation.name(),
                        columnAnnotation.type(),columnAnnotation.length());
                //获取主键
                Primary primary = field.getAnnotation(Primary.class);
                if (primary != null) {
                    //定义主键内容
                    columnBean.setPrimary(true);
                    columnBean.setPrimaryName(primary.name());  //约束名称
                }
                tableBean.addColumn(columnBean);            //保存列信息
            }
        }
        return tableBean;
    }
}
