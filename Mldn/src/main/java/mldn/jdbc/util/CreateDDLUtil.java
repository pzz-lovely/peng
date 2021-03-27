package mldn.jdbc.util;

import java.sql.JDBCType;
import java.util.Map;

/**
 * @Auther lovely
 * @Create 2020-03-30 22:04
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CreateDDLUtil {
    public static String getDDL(Class<?> clazz) {
        StringBuffer sql = new StringBuffer();
        TableBean tableBean = ParseUtil.handler(clazz);
        if (tableBean.isBeforeDelete()) {   //在执行表之前 数据表的删除
            sql.append("DROP TABLE IF EXISTS " + tableBean.getTableName()+";\n");
        }
        sql.append("Create TABLE " + tableBean.getTableName() + "(\n");
        String primary = "";
        int index = 0;
        for (Map.Entry<String, TableBean.ColumnBean> entry : tableBean.getColumnBeans().entrySet()) {
            if(index == 0){
                sql.append(entry.getKey() + " ");
            }else{
                sql.append(", \n"+entry.getKey() + " ");
            }
            sql.append(JDBCType.valueOf(entry.getValue().getType()));
            if(entry.getValue().getLength() == -1 ){
                sql.append(entry.getValue().getLength() );
            }else {
                sql.append("(" + entry.getValue().getLength() + ")");
            }
            if (entry.getValue().isPrimary()) {
                primary =
                        ", \n CONSTRAINT " + entry.getValue().getPrimaryName()+" PRIMARY KEY("+entry.getValue().getName()+")";
            }
            index++;
        }
        if (!"".equals(primary)) {
            sql.append(primary);
        }
        sql.append(") engine=INNODB;");
        return sql.toString();
    }
}
