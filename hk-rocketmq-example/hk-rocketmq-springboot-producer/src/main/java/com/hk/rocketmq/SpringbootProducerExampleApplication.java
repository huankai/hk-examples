package com.hk.rocketmq;

import com.hk.commons.util.date.DatePattern;
import com.hk.commons.util.date.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * rocketmq spring boot 使用例子
 *
 * @author kevin
 * @date 2019-11-21 17:48
 * @see https://github.com/apache/rocketmq-spring
 */
@SpringBootApplication
public class SpringbootProducerExampleApplication implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
           1、 发送消息到 test-topic-1
         */
//        rocketMQTemplate.convertAndSend("test-topic-1", "Hello world!");
//        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello World! I'm from spring message").build());


        /*
            2、发送 自定义对象类型消息
         */
//        rocketMQTemplate.convertAndSend("custom-obj-topic-1", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

        /*
            3、发送消息到指定的 Tag
            topic 命名格式为:  topicName:tagName ，中间用 : 分隔
         */
//        rocketMQTemplate.convertAndSend("tag-topic-1:TagB", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
//
//        /*
//            4、异步发送消息
//         */
//        rocketMQTemplate.asyncSend("async-topic", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.printf("async on success SendResult=%s %n", sendResult);
//            }
//
//            @Override
//            public void onException(Throwable e) {
//                System.err.printf("async onException Throwable=%s %n", e);
//            }
//        });

        /*
            5、同步发送消息
         */
//        SendResult senResult = rocketMQTemplate.syncSend("sync-topic", "Sync Message");
//        System.out.println("syncSend Result:" + JsonUtils.serialize(senResult));

        /*
            6、发送批量消息
         */
//        testBatchMessages();

        /*
         7、 发送事物消息
         */
//        testTransaction();

        /*
        8、发送延迟消息
            参数一:　发送的目的地
            参数二:　 发送的消息
            参数三:　 发送超时时间，单位: 毫秒
            参数四:　 延迟等级、小于等于 0 是不延迟 ，
                    延迟等级为: 1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h
                    设置为 1，延迟1s ，依次类推
         */
        rocketMQTemplate.syncSend("delay-topic",
                MessageBuilder.withPayload("sync delay Message" +
                        DateTimeUtils.localDateTimeToString(LocalDateTime.now(), DatePattern.YYYY_MM_DD_HH_MM_SS)).build(), 3000, 4);
    }


    private static final String TX_GROUP_NAME = "myTxProducerGroup";

    private void testTransaction() throws MessagingException {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {

                org.springframework.messaging.Message<String> msg = MessageBuilder.withPayload("Hello RocketMQ " + i).
                        setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
                SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(TX_GROUP_NAME,
                        "transaction-topic:" + tags[i % tags.length], msg, null);
                System.out.printf("------ send Transactional msg bod  y = %s , sendResult=%s %n",
                        msg.getPayload(), sendResult.getSendStatus());

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RocketMQTransactionListener(txProducerGroup = TX_GROUP_NAME)
    static class TransactionListenerImpl implements RocketMQLocalTransactionListener {

        private AtomicInteger transactionIndex = new AtomicInteger(0);

        private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
            System.out.printf("#### executeLocalTransaction is executed, msgTransactionId=%s %n", transId);
            int value = transactionIndex.getAndIncrement();
            int status = value % 3;
            localTrans.put(transId, status);
            if (status == 0) {
                // Return local transaction with success(commit), in this case,
                // this message will not be checked in checkLocalTransaction()
                System.out.printf("    # COMMIT # 模拟 %s 关联本地事务执行成功! ### %n", msg.getPayload());
                return RocketMQLocalTransactionState.COMMIT;
            }

            if (status == 1) {
                // Return local transaction with failure(rollback) , in this case,
                // this message will not be checked in checkLocalTransaction()
                System.out.printf("    # ROLLBACK # 模拟 %s 关联本地事务执行失败! %n", msg.getPayload());
                return RocketMQLocalTransactionState.ROLLBACK;
            }
            System.out.println("  # UNKNOW # 模拟 %s 关联本地事务执行状态为 UNKNOWN! \n");
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        /**
         * 事物回查
         *
         * @param msg
         * @return
         */
        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
            RocketMQLocalTransactionState retState = RocketMQLocalTransactionState.COMMIT;
            Integer status = localTrans.get(transId);
            if (null != status) {
                switch (status) {
                    case 0:
                        retState = RocketMQLocalTransactionState.UNKNOWN;
                        break;
                    case 1:
                        retState = RocketMQLocalTransactionState.COMMIT;
                        break;
                    case 2:
                        retState = RocketMQLocalTransactionState.ROLLBACK;
                        break;
                }
            }
            System.out.printf("------ !!! checkLocalTransaction is executed once," +
                            " msgTransactionId=%s, TransactionState=%s status=%s %n",
                    transId, retState, status);
            return retState;
        }
    }

    /**
     * 发送批量消息
     */
    private void testBatchMessages() {
        List<org.springframework.messaging.Message> msgs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }
        SendResult sr = rocketMQTemplate.syncSend("batch-topic", msgs, 60000);
        System.out.println("--- Batch messages send result :" + sr);

    }


    @Data
    @AllArgsConstructor
    private static class OrderPaidEvent implements Serializable {
        private String orderId;

        private BigDecimal paidMoney;
    }

}
