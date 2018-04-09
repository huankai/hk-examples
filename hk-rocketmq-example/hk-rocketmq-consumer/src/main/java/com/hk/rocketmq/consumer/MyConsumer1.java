package com.hk.rocketmq.consumer;

import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消息消费
 * 
 * @author kally
 * @date 2018年4月3日下午2:17:23
 */
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MyConsumer1 implements RocketMQListener<String> {

	@Override
	public void onMessage(String message) {
		System.out.println("MyConsumer1: message-----" + message);

	}

}
