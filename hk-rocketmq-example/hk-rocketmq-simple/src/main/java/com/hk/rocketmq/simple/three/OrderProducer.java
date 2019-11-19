package com.hk.rocketmq.simple.three;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 顺序消息生产者
 *
 * 相同订单号的消息，保证严格的消费顺序
 *
 * @author huangkai
 * @date 2019-11-19 23:16
 */
public class OrderProducer {

    private static final List<OrderVo> list;


    private static class OrderVo {

        private String orderNo;

        private String desc;

        public OrderVo() {
        }

        OrderVo(String orderNo, String desc) {
            this.orderNo = orderNo;
            this.desc = desc;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "OrderVo{" +
                    "orderNo='" + orderNo + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    static {
        list = new ArrayList<>();
        String orderNo = UUID.randomUUID().toString().replace("-", "");
        list.add(new OrderVo(orderNo, "创建订单"));
        list.add(new OrderVo(orderNo, "支付"));
        list.add(new OrderVo(orderNo, "发货"));
        list.add(new OrderVo(orderNo, "收货"));

        orderNo = UUID.randomUUID().toString().replace("-", "");
        list.add(new OrderVo(orderNo, "创建订单"));
        list.add(new OrderVo(orderNo, "支付"));
        list.add(new OrderVo(orderNo, "发货"));
        list.add(new OrderVo(orderNo, "收货"));

        orderNo = UUID.randomUUID().toString().replace("-", "");
        list.add(new OrderVo(orderNo, "创建订单"));
        list.add(new OrderVo(orderNo, "支付"));
        list.add(new OrderVo(orderNo, "发货"));
        list.add(new OrderVo(orderNo, "收货"));
    }

    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("simple-producer");
        producer.setNamesrvAddr(Constants.NAME_SERVER);//指定 namesrv 地址，多个使用 ; 分隔
        producer.start();

        for (OrderVo item : list) {
            Message msg = new Message("orderTopicTest",
                    "TagA",
                    item.getOrderNo(),
                    item.toString().getBytes(StandardCharsets.UTF_8));
            /*
                参数一: 消息
                参数二: MessageQueueSelector 消息队列选择器
                参数三: 业务标识参数(如这里是订单号)
             */
            producer.send(msg, new MessageQueueSelector() {

                /*
                参数一: 队列集合
                参数二: 消息内容
                参数三: 业务标识参数 (如这里是订单号)
                 */
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    String orderNo = arg.toString();
                    return mqs.get(Math.abs(orderNo.hashCode()) % mqs.size());
                }
            }, item.getOrderNo());
        }
        producer.shutdown();
    }
}
