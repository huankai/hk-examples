/**
 * 
 */
package com.hk.amqp.example;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2018年3月5日下午2:36:49
 */
@Component
@RabbitListener(queues = { "queue_test" })
public class FanoutReceiver {

	@RabbitHandler
	public void process(String message) {
		System.out.println("Get Message:" + message);
	}
}
