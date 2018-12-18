package com.hk.oracle.example.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 卡交易视图
 *
 * @author kevin
 * @date 2018-09-10 11:38
 */
@Data
@Entity
@Table(name = "ic_cardtransline_view")
public class IcCardTransLineView implements Persistable<String> {

    /**
     *
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     *
     */
    @Column(name = "cardinst_id")
    private String cardInstId;

    /**
     *
     */
    @Column(name = "trandt")
    private String trandt;

    /**
     *
     */
    @Column(name = "posid")
    private String posId;

    /**
     *
     */
    @Column(name = "ttc")
    private String ttc;

    /**
     *
     */
    @Column(name = "trantm")
    private String tranTm;

    /**
     *
     */
    @Column(name = "cardid")
    private String cardId;

    /**
     *
     */
    @Column(name = "appcode")
    private String appcode;

    /**
     *
     */
    @Column(name = "appacccode")
    private String appAccCode;

    /**
     *
     */
    @Column(name = "tradetype")
    private String tradeType;

    /**
     *
     */
    @Column(name = "amount")
    private Double amount;

    /**
     *
     */
    @Column(name = "cardbalance")
    private Double cardBalance;

    /**
     *
     */
    @Column(name = "balance")
    private Double balance;

    /**
     *
     */
    @Column(name = "belongdate")
    private String belongDate;

    /**
     *
     */
    @Column(name = "operator")
    private String operator;

    /**
     *
     */
    @Column(name = "flag")
    private String flag;

    /**
     *
     */
    @Column(name = "retamount")
    private String reTaMount;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
