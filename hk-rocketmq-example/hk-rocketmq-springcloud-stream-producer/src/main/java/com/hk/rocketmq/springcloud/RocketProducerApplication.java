package com.hk.rocketmq.springcloud;

import com.hk.commons.util.IDGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2019-11-22 17:17
 */
@Slf4j
@SpringBootApplication
@EnableBinding(value = {TestOutput.class, TransactionOutput.class, TagOutput.class, DelayOutput.class})
public class RocketProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RocketProducerApplication.class, args);
    }

    @Autowired
    private TransactionOutput transactionOutput;

    @Autowired
    private TagOutput tagOutput;

    @Autowired
    private DelayOutput delayOutput;

    @Override
    public void run(String... args) throws Exception {

        /*
         * 发送事物消息
         * */
//        transactionOutput.output().send(MessageBuilder
//                .withPayload(new MessageVo(IDGenerator.UUID_32.generate(), "transaction message")).build());

        /*
         * 发送消息到指定  tag
         * */
//        tagOutput.output().send(MessageBuilder
//                .withPayload(new MessageVo(IDGenerator.UUID_32.generate(), "Tag message TagB ."))
//                .setHeader(RocketMQHeaders.TAGS, "TagB") // 添加指定  Tag
//                .build());

        delayOutput.output().send(MessageBuilder
                .withPayload(new MessageVo(IDGenerator.UUID_32.generate(), "delay message ..." + LocalDateTime.now()))
                // 添加延迟队列头等级,只能设置等级 ，预设值的延迟时间间隔为：
//                 1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 2)// TODO 延迟队列好像没有生效
                .build());
    }

//    /**
//     * 测试发送消息
//     *
//     * @return
//     */
//    @Bean
//    @InboundChannelAdapter(value = TestOutput.OUTPUT, poller = @Poller(fixedRate = "4000", maxMessagesPerPoll = "1"))
//    public MessageSource<Date> send() {
//        log.debug("RocketProducerApplication send Message ...");
//        return () -> new GenericMessage<>(new Date());
//    }

    /**
     * 发送事物消息
     *
     * @return
     */
//    @Bean
//    @InboundChannelAdapter(value = TransactionOutput.OUTPUT)
//    public MessageSource<MessageVo> transactionSend() {
//        log.debug("RocketProducerApplication send transactionSend ...");
//        return () -> new GenericMessage<>(new MessageVo(IDGenerator.UUID_32.generate(), "transaction message"));
//    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageVo implements Serializable {

        private String id;

        private String message;
    }
}
