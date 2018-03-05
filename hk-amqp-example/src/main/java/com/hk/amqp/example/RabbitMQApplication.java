package com.hk.amqp.example;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author kally
 * @date 2018年3月2日上午11:18:14
 */
@SpringBootApplication
public class RabbitMQApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQApplication.class, args);
	}
	
//	/**
//	 * 交换机名称
//	 */
//	@Value(value = "${amqp.example.fanout.exchange}")
//	private String amqpExampleFanoutExchange;
//	
//	@Bean
//	public FanoutExchange fanoutExchange() {
//		return new FanoutExchange(amqpExampleFanoutExchange);
//	}
	
	@Bean
	public Queue testQueue(){
		return new Queue("queue_test");
	}

}
