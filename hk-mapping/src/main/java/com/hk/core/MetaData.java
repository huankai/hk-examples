package com.hk.core;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.entity.Column;
import com.hk.entity.ConnectionModel;
import com.hk.entity.Table;
import com.hk.util.ConnectionUtils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kevin
 * @date 2018-05-29 13:32
 */
public class MetaData {

    private DatabaseMetaData metaData;

    public MetaData(ConnectionModel connectionModel) {
        try {
            this.metaData = ConnectionUtils.getConnection(connectionModel).getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回所有表名
     *
     * @return
     */
    public List<String> getTableNameList() {
        List<String> result = new ArrayList<>();
        try {
            ResultSet rs = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                result.add(tableName);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回要忽略的表
     *
     * @param ingoreTableNames
     * @return
     */
    public List<Table> getAllTablesIngore(String... ingoreTableNames) {
        ResultSet rs;
        List<Table> tables = new ArrayList<>();
        try {
            rs = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!ArrayUtils.contains(ingoreTableNames, tableName)) {
                    String comment = rs.getString("REMARKS");
                    String primaryKey = getPrimaryKey(tableName);
                    tables.add(new Table(tableName, primaryKey, comment, getColumns(tableName, primaryKey)));
                }
            }
            return tables;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回指定的表
     *
     * @param tableNames
     * @return
     */
    public List<Table> getTables(String... tableNames) {
        if (ArrayUtils.isNotEmpty(tableNames)) {
            return getTables(Arrays.asList(tableNames));
        }
        return getAllTablesIngore();
    }

    /**
     * 返回指定的表
     *
     * @param tableNames
     * @return
     */
    public List<Table> getTables(List<String> tableNames) {
        if (CollectionUtils.isNotEmpty(tableNames)) {
            List<Table> result = new ArrayList<>();
            tableNames.forEach(item -> {
                try {
                    ResultSet rs = metaData.getTables(null, "%", item, new String[]{"TABLE"});
                    if (rs.next()) {
                        String tableName = rs.getString("TABLE_NAME");
                        String comment = rs.getString("REMARKS");
                        String primaryKey = getPrimaryKey(tableName);
                        result.add(new Table(tableName, primaryKey, comment, getColumns(tableName, primaryKey)));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            return result;
        } else {
            return getTables();
        }
    }

    /**
     * 获取表主键名
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    private String getPrimaryKey(String tableName) throws SQLException {
        ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
        return rs.next() ? rs.getString("COLUMN_NAME") : null;
    }

    /**
     * 获取表的所有列
     *
     * @param tableName
     * @param primaryKey
     * @return
     * @throws SQLException
     */
    private List<Column> getColumns(String tableName, String primaryKey) throws SQLException {
        List<Column> columns = new ArrayList<>();
        ResultSet rs = metaData.getColumns(null, "%", tableName, "%");
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String comment = rs.getString("REMARKS");
            String typeName = rs.getString("TYPE_NAME");
            boolean isPrimaryKey = StringUtils.equals(columnName, primaryKey);
            if (isPrimaryKey) {
                columns.add(0, new Column(columnName, true, rs.getBoolean("NULLABLE"), typeName, rs.getInt("DATA_TYPE"), comment));
            } else {
                columns.add(new Column(columnName, false, rs.getBoolean("NULLABLE"), typeName, rs.getInt("DATA_TYPE"), comment));
            }
        }
        return columns;
    }

}
