package mldn.jdbc.util;

import mldn.jdbc.annotation.Column;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther lovely
 * @Create 2020-03-30 21:48
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 描述数据信息解析的结果
 */
public class TableBean {
    private boolean beforeDelete;
    private String tableName;
    private Map<String,ColumnBean> columnBeans;
    public static class ColumnBean{
        private String name;
        private int type;
        private int length;
        private boolean primary;    //是否为主键
        private String primaryName;//约束名称

        public ColumnBean(String name, int type, int length) {
            this.name = name;
            this.type = type;
            this.length = length;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }

        public int getLength() {
            return length;
        }

        public boolean isPrimary() {
            return primary;
        }

        public String getPrimaryName() {
            return primaryName;
        }

        public void setPrimaryName(String primaryName) {
            this.primaryName = primaryName;
        }
    }


    public TableBean() {
    }

    public TableBean(String name, boolean beforeDelete) {
        this.tableName = name;
        this.beforeDelete = beforeDelete;
        this.columnBeans = new HashMap<>();
    }


    public Map<String, ColumnBean> getColumnBeans() {
        return columnBeans;
    }

    public void addColumn(ColumnBean columnBean) {
        this.columnBeans.put(columnBean.name, columnBean);
    }

    public boolean isBeforeDelete() {
        return beforeDelete;
    }

    public String getTableName() {
        return tableName;
    }
}
