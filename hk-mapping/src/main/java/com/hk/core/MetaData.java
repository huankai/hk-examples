package com.hk.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.entity.Column;
import com.hk.entity.ConnectionModel;
import com.hk.entity.Table;
import com.hk.util.ConnectionUtils;
import lombok.AllArgsConstructor;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

/**
 * @author: huangkai
 * @date 2018-05-29 13:32
 */
public class MetaData {

    interface ColumFunction<R, T> {

        Set<String> getImportVars();

        R convert(T t);

    }

    public static class HmpColumnFunction implements ColumFunction<String, Integer> {

        protected static final List<MappingModel> TYPE_CLASS_NAME_MAPPING;

        @AllArgsConstructor
        private static class MappingModel {

            private List<Integer> typeList;

            private String className;

            private String importPackage;

        }

        static {
            TYPE_CLASS_NAME_MAPPING = Lists.newArrayList();
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.BIT, Types.BOOLEAN),
                    "Boolean", null));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.TINYINT, Types.SMALLINT, Types.INTEGER),
                    "Integer", null));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.BIGINT), "Long", null));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.DECIMAL, Types.NUMERIC),
                    "BigDecimal", "java.math.BigDecimal"));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.REAL), "Float", null));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.FLOAT, Types.DOUBLE), "Double", null));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.CHAR, Types.VARCHAR,
                    Types.LONGVARCHAR, Types.BINARY, Types.VARBINARY, Types.LONGVARBINARY),
                    "String", null));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.DATE),
                    "LocalDate", "java.time.LocalDate"));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.TIME),
                    "LocalTime", "java.time.LocalTime"));
            TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.TIMESTAMP),
                    "LocalDateTime", "java.time.LocalDateTime"));
        }

        private Set<String> importVars = Sets.newHashSet();

        @Override
        public Set<String> getImportVars() {
            return importVars;
        }

        @Override
        public String convert(Integer type) {
            Optional<MappingModel> optional = TYPE_CLASS_NAME_MAPPING.stream()
                    .filter(item -> item.typeList.contains(type)).findFirst();
            if (optional.isPresent()) {
                MappingModel model = optional.get();
                if (null != model.importPackage) {
                    importVars.add(model.importPackage);
                }
                importVars.add(model.className);
            }
            return "Object";
        }
    }

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
        List<String> result = Lists.newArrayList();
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
                    tables.add(new Table(tableName, primaryKey, comment, StringUtils.lineToBigHump(tableName), StringUtils.lineToSmallHump(primaryKey), getColumns(tableName, primaryKey)));
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
                        result.add(new Table(tableName, primaryKey, comment, StringUtils.lineToBigHump(tableName), StringUtils.lineToSmallHump(primaryKey), getColumns(tableName, primaryKey)));
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
            case Types.DECIMAL:
            case Types.NUMERIC:
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
                return "LocalDate";
            case Types.TIME:
                return "LocalTime";
            case Types.TIMESTAMP:
                return "LocalDateTime";
            default:
                return "Object";
        }
    }

}
