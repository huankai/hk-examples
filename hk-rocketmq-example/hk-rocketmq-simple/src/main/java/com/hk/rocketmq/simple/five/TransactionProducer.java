package com.hk.rocketmq.simple.five;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事物消息生产者
 *
 * @author huangkai
 * @date 2019-11-21 11:10
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("transcation-producer-group");
        producer.setNamesrvAddr(Constants.NAME_SERVER);//指定 namesrv 地址，多个使用 ; 分隔
        String[] tags = {"TagA", "TagB", "TagC"};
        producer.setTransactionListener(new TransactionListener() {

            /**
             * 在该方法中执行本地事物
             * @param msg
             * @param arg
             * @return
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                if (StringUtils.equals(msg.getTags(), "TagA")) {
                    return LocalTransactionState.COMMIT_MESSAGE;// rocketmq 提交消息
                }
                if (StringUtils.equals(msg.getTags(), "TagB")) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;// rocketmq 会回滚消息，该消息会丢弃
                }
                return LocalTransactionState.UNKNOW; // rocketmq 会会查消息，会调用 checkLocalTransaction 方法
            }

            /**
             * 消息事物回查方法，此方法返回值如下:
             *      LocalTransactionState.COMMIT_MESSAGE : 提交消息，消费者无可以消费
             *      LocalTransactionState.ROLLBACK_MESSAGE : 回滚消息，消费者无法消费，该消息会丢弃
             *      LocalTransactionState.UNKNOW : 回滚消息，消费者无法消费，该消息会丢弃
             * @param msg
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("checkLocalTransaction:" + msg.getTags());
                return LocalTransactionState.COMMIT_MESSAGE; // 提交消息
            }
        });
        producer.start();
        for (int i = 0; i < tags.length; i++) {
            TransactionSendResult result = producer.sendMessageInTransaction(new Message("transcationTopicTest",
                    tags[i],
                    "",
                    "Delay Message Transaction ".getBytes()), null);
            System.out.println(result);

        }

//        producer.shutdown();
    }
}
