package com.hk.example1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.annotations.WriteExcelField;
import com.hk.commons.util.BooleanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExcelVo {

    @ReadExcelField(start = 0)
    @WriteExcelField(index = 0, value = "名称")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ReadExcelField(start = 1)
    @WriteExcelField(index = 1, value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ReadExcelField(start = 2)
    @WriteExcelField(index = 2, value = "生日")
    private LocalDate brithday;

    @ReadExcelField(start = 3)
    private Boolean girl;

    @ReadExcelField(start = 4)
    @WriteExcelField(index = 4, value = "创建日期", width = 30)
    private LocalDateTime createTime;

    public ExcelVo() {
    }

    public ExcelVo(String name, Integer age, LocalDate brithday, Boolean girl, LocalDateTime createTime) {
        this.name = name;
        this.age = age;
        this.brithday = brithday;
        this.girl = girl;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBrithday() {
        return brithday;
    }

    public void setBrithday(LocalDate brithday) {
        this.brithday = brithday;
    }

    public Boolean getGirl() {
        return girl;
    }

    @WriteExcelField(index = 3, value = "是否为女孩")
    @JsonIgnore
    public String getGirlChinease() {
        return BooleanUtils.toBooleanChinese(girl);
    }

    public void setGirl(Boolean girl) {
        this.girl = girl;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
