package com.hk.rocketmq.springcloud.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * 事物监听器
 *
 * @author kevin
 * @date 2019-11-23 11:07
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "transaction-stream-output-group")
public class MessageLocalTransactionListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//        byte[] payload = (byte[]) msg.getPayload(); // 获取消息内容
//        Object object = JsonUtils.deserialize(payload, Object.class);
        log.debug("MessageLocalTransactionListener executeLocalTransaction....");
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
