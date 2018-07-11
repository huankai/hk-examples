/**
 * 
 */
package com.hk.rocketmq.producer;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 生产者
 * 
 * @author: kevin
 * @date 2018年4月3日下午1:11:56
 */
@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	/**
	 * 启动后会运行，发送消息
	 */
	@Override
	public void run(String... args) throws Exception {
		rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
		rocketMQTemplate.send("test-topic-1",
				MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
		rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

		/*
		 * 一旦rocketmqtemplate被摧毁，你不能发送任何消息了
		 */
//		rocketMQTemplate.destroy();
	}

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

}
