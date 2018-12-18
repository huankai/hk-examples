package com.hk.oracle.example.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户资料
 *
 * @author kevin
 * @date 2018-09-10 11:30
 */
@Data
@Entity
@Table(name = "ic_customer")
public class IcCustomer implements Persistable<String> {

    @Id
    @Column(name = "customer_id")
    private String customerId;

    /**
     * 所属机构节点
     */
    @Column(name = "inst_id")
    private String instId;

    /**
     * 客户属性,0-默认(不记名),1-普通客户(记名),将来可用其它值表示客户等级
     */
    @Column(name = "attr")
    private String attr;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 拼音名
     */
    @Column(name = "nm_spelling")
    private String nmSpelling;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 性别:0-未知的性别,1-男性,2-女性,9-未说明的性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 民族
     */
    @Column(name = "nation")
    private String nation;

    /**
     * 地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 邮编
     */
    @Column(name = "zip")
    private String zip;

    /**
     * 手机
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 住宅电话
     */
    @Column(name = "tele_phone")
    private String telePhone;

    /**
     * 证件类型Ic_idtype.idtype
     */
    @Column(name = "id_ty")
    private String idTy;

    /**
     * 证件号码
     */
    @Column(name = "id_cd")
    private String idCd;

    /**
     * 证件有效期
     */
    @Column(name = "id_expire_date")
    private String idExpireDate;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 单位编号
     */
    @Column(name = "company_id")
    private String companyId;

    /**
     * <p>
     * 0-正常,1-已销户
     * </p>
     */
    @Column(name = "status")
    private String status;

    /**
     * 录取省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 学院名称ic_department.departmentid
     */
    @Column(name = "department")
    private String department;

    /**
     * 专业（部门）ic_major.majorid
     */
    @Column(name = "major")
    private String major;

    /**
     * 学工号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 年级
     */
    @Column(name = "grade")
    private String grade;

    /**
     * 身份名称ic_identity.identityid，研究生，本科生，远程教育函授生，教工，其他
     */
    @Column(name = "identity")
    private String identity;

    /**
     * 资料更新日期
     */
    @Column(name = "update_dt")
    private String updateDt;

    /**
     * 资料更新时间
     */
    @Column(name = "update_tm")
    private String updateTm;

    /**
     * 客户证件号是否已办理过大信通卡，0-未办理过，1-办理过
     */
    @Column(name = "is_hadc_ard")
    private String isHadCard;

    /**
     * 批次号ic_customer_batchmsg.batchid
     */
    @Column(name = "batch_id")
    private String batchId;


    @Override
    public String getId() {
        return customerId;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
