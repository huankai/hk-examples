package com.hk.rabbit.transation;


import com.hk.commons.util.StringUtils;
import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * 生产者 异步发送消息
 */
public class AsynProducer {

    final static String ASYN_QUEUE_NAME = "asyn_queue_name";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(ASYN_QUEUE_NAME, false, false, false, null);

        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {

            /**
             * 消息成功发送
             * @throws IOException
             */
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                System.out.println("已确认消息，handleAck:" + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }

            /**
             * 消息发送失败
             * @throws IOException
             */
            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                System.out.println("未确认消息,handleNack:" + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            long nextPublishSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish(StringUtils.EMPTY, ASYN_QUEUE_NAME, null, RandomStringUtils.randomNumeric(10).getBytes());
            confirmSet.add(nextPublishSeqNo);

        }

    }
}
