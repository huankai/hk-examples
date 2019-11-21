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


/**
 * 顺序消息生产者
 * <p>
 * 相同订单号的消息，保证严格的消费顺序
 *
 * @author huangkai
 * @date 2019-11-19 23:16
 */
public class OrderProducer {

    private static final List<OrderVo> list;


    private static class OrderVo {

        private Integer orderNo;

        private String desc;

        public OrderVo() {
        }

        OrderVo(Integer orderNo, String desc) {
            this.orderNo = orderNo;
            this.desc = desc;
        }

        public Integer getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Integer orderNo) {
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
                    "orderNo=" + orderNo +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    static {
        list = new ArrayList<>();
        list.add(new OrderVo(1, "创建订单"));
        list.add(new OrderVo(1, "支付"));
        list.add(new OrderVo(1, "发货"));
        list.add(new OrderVo(1, "收货"));

        list.add(new OrderVo(2, "创建订单"));
        list.add(new OrderVo(2, "支付"));
        list.add(new OrderVo(2, "发货"));
        list.add(new OrderVo(2, "收货"));

        list.add(new OrderVo(3, "创建订单"));
        list.add(new OrderVo(3, "支付"));
        list.add(new OrderVo(3, "发货"));
        list.add(new OrderVo(3, "收货"));
    }

    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("order-producer");
        producer.setNamesrvAddr(Constants.NAME_SERVER);//指定 namesrv 地址，多个使用 ; 分隔
        producer.start();

        for (OrderVo item : list) {
            Message msg = new Message("orderTopicTest",
                    "TagA",
                    item.getOrderNo().toString(),
                    item.toString().getBytes(StandardCharsets.UTF_8));
//            msg.setDelayTimeLevel();
            /*
                参数一: 消息
                参数二: MessageQueueSelector 消息队列选择器
                参数三: 业务标识参数(如这里是订单id)
             */
            producer.send(msg, new MessageQueueSelector() {

                /*
                参数一: 队列集合
                参数二: 消息内容
                参数三: 业务标识参数 (如这里是订单id)
                 */
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    int id = Integer.parseInt(arg.toString());
                    return mqs.get(id % mqs.size());
                }
            }, item.getOrderNo());
        }
        producer.shutdown();
    }
}
