package com.hk.rocketmq.consumer;

import com.hk.commons.util.JsonUtils;
import com.hk.rocketmq.consumer.domain.OrderPaidEvent;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消息消费
 * 
 * @author: kevin
 * @date 2018年4月3日下午2:17:23
 */
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {

	@Override
	public void onMessage(OrderPaidEvent message) {
		System.out.println("MyConsumer2: message-----" + JsonUtils.toJSONString(message));

	}

}
