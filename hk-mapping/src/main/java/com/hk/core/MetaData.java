package com.hk.core;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.StringUtils;
import com.hk.entity.Column;
import com.hk.entity.Table;
import com.hk.util.ImportVar;
import com.hk.util.TableAssistant;

import java.sql.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author: huangkai
 * @date 2018-05-29 13:32
 */
public class MetaData {

    private DatabaseMetaData metaData;

    public MetaData(Connection connection) {
        try {
            this.metaData = connection.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Table> getTables() {
        ResultSet rs;
        List<Table> tables = new ArrayList<>();
        List<String> ingoreTables = TableAssistant.ingoreTables();
        try {
            rs = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!ingoreTables.contains(tableName)) {
                    String comment = rs.getString("REMARKS");
                    String primaryKey = getPrimaryKey(tableName);
                    tables.add(new Table(tableName, primaryKey, comment, StringUtils.lineToBigHump(tableName), StringUtils.lineToSmallHump(primaryKey), getColumns(tableName, primaryKey)));
                }
            }
            return tables;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Table> getTables(String... tableNames) {
        List<Table> result = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(tableNames)) {
            Stream.of(tableNames).forEach(item -> {
                try {
                    ResultSet rs = metaData.getTables(null, "%", item, new String[]{"TABLE"});
                    if (rs.next()) {
                        String tableName = rs.getString("TABLE_NAME");
                        String comment = rs.getString("REMARKS");
                        String primaryKey = getPrimaryKey(tableName);
                        result.add(new Table(tableName, primaryKey, comment, StringUtils.lineToBigHump(tableName), StringUtils.lineToSmallHump(primaryKey), getColumns(tableName, primaryKey)));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return result;
    }


    private String getPrimaryKey(String tableName) throws SQLException {
        ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
        return rs.next() ? rs.getString("COLUMN_NAME") : null;
    }

    private List<Column> getColumns(String tableName, String primaryKey) throws SQLException {
        List<Column> columns = new ArrayList<>();
        ResultSet rs = metaData.getColumns(null, "%", tableName, "%");
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String className = getClassName(rs.getInt("DATA_TYPE"));
            String comment = rs.getString("REMARKS");
            String typeName = rs.getString("TYPE_NAME");
            boolean isPrimaryKey = StringUtils.equals(columnName, primaryKey);
            if (isPrimaryKey) {
                columns.add(0, new Column(columnName, StringUtils.lineToSmallHump(columnName), true, rs.getBoolean("NULLABLE"), typeName, className, comment));
            } else {
                columns.add(new Column(columnName, StringUtils.lineToSmallHump(columnName), false, rs.getBoolean("NULLABLE"), typeName, className, comment));
            }
        }
        return columns;
    }

    private String getClassName(int type) {
        switch (type) {
            case Types.BIT:
            case Types.BOOLEAN:
                return "Boolean";

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                return "Integer";

            case Types.BIGINT:
                return "Long";

            //		case Types.INTEGER:
            //			ImportVar.put("java.math.BigInteger");
            //			return "BigInteger";

            case Types.DECIMAL:
            case Types.NUMERIC:
                ImportVar.put("java.math.BigDecimal");
                return "BigDecimal";

            case Types.REAL:
                return "Float";

            case Types.FLOAT:
            case Types.DOUBLE:
                return "Double";

            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                return "String";

            case Types.DATE:
                ImportVar.put("java.time.LocalDate");
                return "LocalDate";
            case Types.TIME:
                ImportVar.put("java.time.LocalTime");
                return "LocalTime";
            case Types.TIMESTAMP:
                ImportVar.put("java.time.LocalDateTime");
                return "LocalDateTime";
            default:
                return "Object";
        }
    }

}
