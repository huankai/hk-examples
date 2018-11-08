package com.hk.entity;

import com.google.common.collect.Lists;
import com.hk.commons.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Types;
import java.util.List;

import static com.hk.entity.Column.MappingModel.STRING_MAPPING;

/**
 * 列实体
 *
 * @author: kevin
 * @date: 2018-05-29 11:03
 */
@Data
public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 是否为主键
     */
    private boolean primaryKey;

    /**
     * 是否可以为空
     */
    private boolean nullable;

    /**
     * 列类型
     */
    private String type;

    /**
     * 列字段对应Java类型名
     */
    private String fieldType;

    /**
     *
     */
    private String fieldSimpleType;

    /**
     * 注释
     */
    private String comment;

    public Column(String name, boolean primaryKey, boolean nullable,
                  String type, int dataType, String comment) {
        this.name = name;
        this.fieldName = StringUtils.lineToSmallHump(name);
        this.primaryKey = primaryKey;
        this.nullable = nullable;
        this.type = type;
        MappingModel mappingModel = TYPE_CLASS_NAME_MAPPING.stream().filter(item -> item.typeList.contains(dataType))
                .findFirst().orElse(STRING_MAPPING);
        fieldType = mappingModel.className;
        if(StringUtils.indexOf(fieldType,".") != -1){
            fieldSimpleType = StringUtils.substringAfterLast(fieldType, ".");
        } else {
            fieldSimpleType = fieldType;
        }
        this.comment = comment;
    }

    /**
     * 首字母大写
     *
     * @return
     */
    public String getCapitalizeFileName() {
        return StringUtils.capitalize(fieldName);
    }

    protected static final List<MappingModel> TYPE_CLASS_NAME_MAPPING;

    static {
        TYPE_CLASS_NAME_MAPPING = Lists.newArrayList();
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.BIT, Types.BOOLEAN), "Boolean"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.TINYINT, Types.SMALLINT, Types.INTEGER), "Integer"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.BIGINT), "Long"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.DECIMAL, Types.NUMERIC), "java.math.BigDecimal"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.REAL), "Float"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.FLOAT, Types.DOUBLE), "Double"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR, Types.BINARY, Types.VARBINARY, Types.LONGVARBINARY),
                "String"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.DATE), "java.time.LocalDate"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.TIME), "java.time.LocalTime"));
        TYPE_CLASS_NAME_MAPPING.add(new MappingModel(Lists.newArrayList(Types.TIMESTAMP), "java.time.LocalDateTime"));
    }

    @AllArgsConstructor
    protected static final class MappingModel {

        protected static final MappingModel STRING_MAPPING = new MappingModel("String");

        private List<Integer> typeList;

        String className;

        private MappingModel(String className) {
            this.className = className;
        }

    }
}
