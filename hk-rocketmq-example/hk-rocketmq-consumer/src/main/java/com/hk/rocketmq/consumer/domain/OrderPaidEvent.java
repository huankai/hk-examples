package com.hk.rocketmq.consumer.domain;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class OrderPaidEvent implements Serializable {

	private String orderId;

	private BigDecimal paidMoney;

	/**
	 * 
	 */
	public OrderPaidEvent() {
	}

	/**
	 * @param orderId
	 * @param paidMoney
	 */
	public OrderPaidEvent(String orderId, BigDecimal paidMoney) {
		this.orderId = orderId;
		this.paidMoney = paidMoney;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the paidMoney
	 */
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	/**
	 * @param paidMoney
	 *            the paidMoney to set
	 */
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

}
