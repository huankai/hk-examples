package com.hk.example.rabbit.stream;

import com.hk.commons.util.JsonUtils;
import com.rabbitmq.client.Channel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huangkai
 * @date 2019-01-17 14:39
 */
@Slf4j
@SpringBootApplication
@EnableBinding(value = {PublishInput.class, RouteKeyInput.class, TopicInput.class,
        DelayedInput.class, AcknowledgeModeInput.class, AutoBindDlqInput.class})
public class Consumer01Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer01Application.class, args);
    }

    /**
     * 发布订阅模式
     *
     * @param date
     */
    @StreamListener(PublishInput.INPUT)
    public void receive(@Payload Date date) {
        log.info("Consumer01Application Receive message : {}", date);
    }

    /**
     * 发布、订阅模式，使用 routeKey
     *
     * @param date
     */
    @StreamListener(RouteKeyInput.INPUT)
    public void routeKeyReceive(@Payload Date date) {
        log.info("Consumer01Application routeKeyReceive message : {}", date);
    }

    /**
     * topic 模式
     *
     * @param date
     */
    @StreamListener(TopicInput.INPUT)
    public void topicReceive(@Payload Date date) {
        log.info("Consumer01Application topicReceive message : {}", date);
    }

    /* ************************** 死信队列 ***********************************************/

    /**
     * 消息重试次数记录，可以使用 redis 作为缓存记录
     */
    private ConcurrentHashMap<String, Integer> dlqMap = new ConcurrentHashMap<>();


    @Getter
    @Setter
    public static class MessageVo implements Serializable {

        /**
         * 唯一
         */
        private String id;

        private String message;
    }

    /**
     * 死信队列
     *
     * @param message message
     */
    @StreamListener(AutoBindDlqInput.INPUT)
    public void autoBindDlqReceive(@Payload MessageVo message) {
        String key = generateCacheKey(AutoBindDlqInput.INPUT, message.getId());
        try {
            log.info("Consumer01Application autoBindDlqReceive message:{}", JsonUtils.serialize(message, true));
//            if (StringUtils.isEmpty("")) {
//                throw new RuntimeException("模拟异常...");
//            }
            dlqMap.remove(key);
        } catch (Exception e) {
            Integer value = dlqMap.getOrDefault(key, 0);
            // 超过3次进入死信队列,当抛出  AmqpRejectAndDontRequeueException 异常，rabbit 会做特殊处理，将消息放入死信队列
            // 消费都 必须开启死信队列
            if (value == 3) {
                // @see http://blog.didispace.com/spring-cloud-starter-finchley-7-4/
                // @see https://github.com/huankai/SpringCloud-Learning
                throw new AmqpRejectAndDontRequeueException(e.getMessage(), e);
            } else {
                dlqMap.put(key, ++value);
                throw e;
            }
        }
    }

    private String generateCacheKey(String prefix, String key) {
        return String.format("%s:%s", prefix, key);
    }

    /* ************************** 手动确认 ***********************************************/

    /**
     * <pre>
     *
     * 消费者手动确认模式
     * @see  https://blog.csdn.net/yjw123456/article/details/85011664
     * </pre>
     *
     * @param message     消息内容
     * @param channel     channel
     * @param deliveryTag deliveryTag
     */
    @StreamListener(AcknowledgeModeInput.INPUT)
    public void acknowledgeModeReceive(String message,
                                       @Header(value = AmqpHeaders.CHANNEL) Channel channel,
                                       @Header(value = AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        log.info("Consumer01Application acknowledgeModeReceive message:{}", message);
        //手动确认消息，参数2的值为false 时确认收到消息, true 为拒接收到消息,消息会被rabbitmq 丢弃，即使开启了死信队列，消息也不会进入死信队列中
        channel.basicAck(deliveryTag, false);
    }

    /* ************************** 延时队列 ***********************************************/

    /**
     * 延时队列消费
     */
    @StreamListener(DelayedInput.INPUT)
    public void delayedReceive(@Payload Date date) {
        log.info("消息接收时间 : {}", LocalDateTime.now());
        log.info("Consumer01Application delayedReceive message : {}", date);
    }
}
