/**
 *
 */
package com.hk.example3;

import com.google.common.collect.Lists;
import com.hk.commons.poi.excel.annotations.NestedProperty;
import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.annotations.WriteExcelField;
import com.hk.example1.ExcelVo;

import java.util.List;

/**
 * @author kevin
 * @date 2018年1月16日上午10:48:15
 */
public class IterableNestedExcelVo {

    @WriteExcelField(index = 5, value = "id")
    @ReadExcelField(start = 5)
    private String id;

    @WriteExcelField(index = 6, value = "名称")
    @ReadExcelField(start = 6)
    private String name;

    @WriteExcelField(index = 7, value = "Value", isStatistics = true)
    @ReadExcelField(start = 7)
    private Integer value;

    @NestedProperty
    private List<ExcelVo> userList;

    /**
     *
     */
    public IterableNestedExcelVo() {

    }

    /**
     *
     */
    public IterableNestedExcelVo(String id, String name, Integer value, ExcelVo excelVos) {
        this.id = id;
        this.name = name;
        this.value = value;
        if (null != excelVos) {
            this.userList = Lists.newArrayList(excelVos);
        }

    }

    /**
     * @param id
     * @param userList
     */
    public IterableNestedExcelVo(String id, String name, Integer value, List<ExcelVo> userList) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.userList = userList;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the userList
     */
    public List<ExcelVo> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<ExcelVo> userList) {
        this.userList = userList;
    }

}
