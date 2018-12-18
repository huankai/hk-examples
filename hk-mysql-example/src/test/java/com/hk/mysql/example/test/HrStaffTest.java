package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.annotations.WriteExcel;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.write.HSSFWriteableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.util.BeanUtils;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sjq-278
 * @date 2018-12-14 15:44
 */
public class HrStaffTest {

    @Data
    public static class HrStaff {

        @ReadExcel(start = 0)
        @WriteExcel(index = 0, value = "学校名称(必填)")
        private String schoolName;

        @ReadExcel(start = 1)
        @WriteExcel(index = 1, value = "姓名（必录）")
        private String userName;

        @ReadExcel(start = 2)
        @WriteExcel(index = 2, value = "工号（必录）")
        private String jobNo;

        @ReadExcel(start = 3)
        @WriteExcel(index = 3, value = "性别（必录）")
        private String sex;

        @ReadExcel(start = 4)
        @WriteExcel(index = 4, value = "手机号")
        private String phone;

        @ReadExcel(start = 5)
        @WriteExcel(index = 5, value = "证件号码")
        private String idCard;
    }


    @Test
    public void hrStaffTest() throws FileNotFoundException {
        ReadParam<HrStaff> param = ReadParam.<HrStaff>builder().beanClazz(HrStaff.class).build();
        ReadableExcel<HrStaff> readableExcel = new DomReadExcel<>(param);
        ReadResult<HrStaff> readResult = readableExcel.read(new File("C:/Users/sjq-278/Downloads/se_staff.xlsx"));
        List<HrStaff> hrStaffs = new ArrayList<>();
        for (HrStaff hrstaff : readResult.getAllSheetData()) {
            String oldJobNo = hrstaff.getJobNo();
            hrstaff.setJobNo(oldJobNo + "gly");
            hrStaffs.add(hrstaff);

            HrStaff xz = new HrStaff();
            BeanUtils.copyProperties(hrstaff, xz);
            xz.setUserName("校长");
            xz.setJobNo(oldJobNo + "xz");
            hrStaffs.add(xz);
        }
        WriteParam<HrStaff> writeParam = WriteParam.<HrStaff>builder()
                .data(hrStaffs)
                .beanClazz(HrStaff.class).build();
        WriteableExcel<HrStaff> writeableExcel = new HSSFWriteableExcel<>();
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Downloads/se_staff2.xlsx")));
    }
}
