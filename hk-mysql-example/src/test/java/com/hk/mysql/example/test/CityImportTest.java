package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.util.JsonUtils;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author huangkai
 * @date 2019-01-04 10:15
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class CityImportTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    @Data
    public static class ExcelVo {

        @ReadExcelField(start = 0)
        private String code;

        @ReadExcelField(start = 1)
        private String name;

        @ReadExcelField(start = 2)
        private String parentId;

        @ReadExcelField(start = 3)
        private String shortName;

        @ReadExcelField(start = 4)
        private Byte cityType;

        @ReadExcelField(start = 5)
        private String areaCode;

        @ReadExcelField(start = 6)
        private String zipCode;

        @ReadExcelField(start = 7)
        private String mergerName;

        @ReadExcelField(start = 8)
        private Double lng;

        @ReadExcelField(start = 9)
        private Double lat;

        @ReadExcelField(start = 10)
        private String pinyin;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class City implements Serializable {

        private String id;

        /**
         * 行政代码
         */
        private String code;

        private String parentId;

        private Byte cityType;

        /**
         * 全称
         */
        private String fullName;

        private String areaCode;

        /**
         * 简名
         */
        private String shortName;

        private String mergerName;

        /**
         * 邮编
         */
        private String postOffice;

        private String pinyin;

        private Double longitude;

        private Double latitude;

        private String createdBy = "0";

        private String description;

        /**
         * 创建时间
         */
        private LocalDateTime createdDate = LocalDateTime.now();

        /**
         * 最后更新用户
         */
        private String lastModifiedBy = "0";

        /**
         * 最后更新时间
         */
        private LocalDateTime lastModifiedDate = LocalDateTime.now();
    }

    @Test
    public void test() {
        ReadParam<ExcelVo> readParam = ReadParam.<ExcelVo>builder().beanClazz(ExcelVo.class).build();
        ReadableExcel<ExcelVo> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<ExcelVo> readResult = readableExcel.read(new File("C:\\Users\\sjq-278\\Desktop/1.xls"));
        List<ExcelVo> allSheetData = readResult.getAllSheetData();
        JsonUtils.serializeToFile(allSheetData, new File("C:\\Users\\sjq-278\\Desktop/2.txt"));
//        System.out.println(JsonUtils.serialize(allSheetData, true));
//        System.out.println(allSheetData.size());

//        List<City> cities = new ArrayList<>();
//        for (ExcelVo vo : allSheetData) {
//            String parentId = cities.stream().filter(item -> StringUtils.equals(item.getCode(), vo.getParentId()))
//                    .map(City::getId)
//                    .findFirst().orElse("0");
//            String id = IDGenerator.STRING_UUID.generate();
//            cities.add(City.builder().
//                    id(id)
//                    .parentId(parentId)
//                    .code(vo.getCode())
//                    .areaCode(vo.areaCode)
//                    .cityType(vo.cityType)
//                    .fullName(vo.name)
//                    .pinyin(vo.getPinyin())
//                    .shortName(vo.shortName)
//                    .postOffice(vo.zipCode)
//                    .longitude(vo.lng)
//                    .mergerName(vo.mergerName)
//                    .latitude(vo.lat)
//                    .lastModifiedBy("8bb70a2cf0a94c44b4f86fd711e42acd")
//                    .createdBy("8bb70a2cf0a94c44b4f86fd711e42acd")
//                    .createdDate(LocalDateTime.now())
//                    .lastModifiedDate(LocalDateTime.now())
//                    .build());
//        }
////        System.out.println(JsonUtils.serialize(cities, true));
//        jdbcSession.batchUpdate("INSERT INTO`sys_city`(`id`, `parent_id`, `code`, `area_code`, `city_type`, `full_name`, `short_name`,pinyin,merger_name, `post_office`, `longitude`, `latitude`, `description`, " +
//                "`created_by`, `created_date`, `last_modified_by`, `last_modified_date`) " +
//                "VALUES (:id, :parentId, :code, :areaCode, :cityType, :fullName, :shortName,:pinyin,:mergerName, :postOffice, :longitude, :latitude, :description, :createdBy, " +
//                ":createdDate, :lastModifiedBy, :lastModifiedDate)", cities);

    }
}
