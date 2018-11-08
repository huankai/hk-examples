package com.hk.oracle.example.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 卡资料
 *
 * @author: kevin
 * @date: 2018-09-10 10:54
 */
@Data
@Entity
@Table(name = "ic_card")
public class IcCard implements Persistable<String> {

    /**
     * 卡号
     */
    @Id
    @Column(name = "card_id")
    private String cardId;

    /**
     * 卡号序号,类似卡号不变的补换卡次数，预留
     */
    @Column(name = "card_seq_id")
    private String cardSeqId;

    /**
     * 所属机构节点
     */
    @Column(name = "inst_id")
    private String instId;

    /**
     * 客户ID
     */
    @Column(name = "customer_id")
    private String customerId;

    /**
     * 卡类ic_cardtype.cardtype
     */
    @Column(name = "card_type")
    private String cardType;

    /**
     * <p>
     * 卡客户属性: <br/>
     * 0 :个人    <br/>
     * 1 :单位卡   <br/>
     * </p>
     */
    @Column(name = "attr")
    private String attr;

    /**
     * <p>
     * 主副卡标志: <br/>
     * S:副卡     <br/>
     * M:主卡     <br/>
     * </p>
     */
    @Column(name = "master_flag")
    private String masterFlag;

    /**
     * 借记卡号,EC圈存用,可以是外部系统卡号
     */
    @Column(name = "debit_card_id")
    private String debitCardId;

    /**
     * 主卡卡号,副卡时有值
     */
    @Column(name = "master_card_id")
    private String masterCardId;

    /**
     * 有效期
     */
    @Column(name = "expire_date")
    private String expireDate;

    /**
     * 开卡日期
     */
    @Column(name = "open_date")
    private String openDate;

    /**
     * 批号,来自ic_taskmaster.batchid
     */
    @Column(name = "batch_id")
    private String batchId;

    /**
     * 制卡日期
     */
    @Column(name = "issue_date")
    private String issueDate;

    /**
     * 启用日期
     */
    @Column(name = "start_date")
    private String startDate;

    /**
     * 销卡日期
     */
    @Column(name = "close_date")
    private String closeDate;

    /**
     * 卡状态 ic_cardstu.cardstu
     */
    @Column(name = "status")
    private String status;

    /**
     * 二磁道信息
     */
    @Column(name = "track2")
    private String track2;

    /**
     * 校园卡种类ic_cardkind.kindid，00-学生卡 01-教工卡 02-校友卡 03-临时卡
     */
    @Column(name = "card_kind")
    private String cardKind;

    @Override
    public String getId() {
        return cardId;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
