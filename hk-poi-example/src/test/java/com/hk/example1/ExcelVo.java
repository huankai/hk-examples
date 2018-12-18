package com.hk.example1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.annotations.WriteExcel;
import com.hk.commons.util.BooleanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExcelVo {

    @ReadExcel(start = 0)
    @WriteExcel(index = 0, value = "名称")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ReadExcel(start = 1)
    @WriteExcel(index = 1, value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ReadExcel(start = 2)
    @WriteExcel(index = 2, value = "生日")
    private LocalDate brithday;

    @ReadExcel(start = 3)
    private Boolean girl;

    @ReadExcel(start = 4)
    @WriteExcel(index = 4, value = "创建日期", width = 30)
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

    @WriteExcel(index = 3, value = "是否为女孩")
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
