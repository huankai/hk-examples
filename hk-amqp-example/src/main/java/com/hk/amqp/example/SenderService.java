/**
 * 
 */
package com.hk.amqp.example;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送
 * 
 * @author kally
 * @date 2018年3月5日下午1:35:30
 */
@Service
public class SenderService {

//	/**
//	 * 交换机名称
//	 */
//	@Value(value = "${amqp.example.fanout.exchange}")
//	private String amqpExampleFanoutExchange;

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public void send(String message) {
		amqpTemplate.convertAndSend("queue_test", message);
//		amqpTemplate.convertAndSend(amqpExampleFanoutExchange, null, message);
	}

}
