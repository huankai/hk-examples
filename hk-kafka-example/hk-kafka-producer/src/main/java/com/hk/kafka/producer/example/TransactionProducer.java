package com.hk.kafka.producer.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author sjq-278
 * @date 2018-11-19 16:36
 */
@Slf4j
public class TransactionProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "sjq-01:9092,sjq-02:9092,sjq-03:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");//等待分区的所有副本应答，才表示此消息发送成功
        props.put(ProducerConfig.RETRIES_CONFIG, 5); // 生产者从服务器收到临时性错误时，生产者重发消息的次数
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);// 一批消息处理大小
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // 请求延时
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // 发送缓存区内存大小
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key序列化
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value序列化

        //设置事务id
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tran");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        //初始化事务
        producer.initTransactions();

        // 开始事务
        producer.beginTransaction();

        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                log.info("index = {} ...", i);
                if (i == 5) {// 模拟异常
                    throw new RuntimeException("Error...");
                }
                producer.send(new ProducerRecord<>("transaction-topic", "data" + i));
            }
            //事务提交
            producer.commitTransaction();
//            producer.send
        } catch (Exception e) {
            //放弃事务
            producer.abortTransaction();
        } finally {
            producer.close();
        }
    }
}
