package com.hk.rabbit.transation;


import com.hk.commons.util.StringUtils;
import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

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
        channel.addConfirmListener(new ConfirmListener() {

            /**
             *
             * @param deliveryTag
             * @param multiple
             * @throws IOException
             */
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }

            /**
             *
             * @param deliveryTag
             * @param multiple
             * @throws IOException
             */
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }
        });
        String message = "";
        while (true) {
            long nextPublishSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish(StringUtils.EMPTY, ASYN_QUEUE_NAME, null, message.getBytes());
            confirmSet.add(nextPublishSeqNo);
        }


    }
}
