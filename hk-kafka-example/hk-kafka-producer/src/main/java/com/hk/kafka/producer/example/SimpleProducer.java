package com.hk.kafka.producer.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author: kevin
 * @date: 2018-08-30 15:58
 */
public class SimpleProducer {

    private static final Logger Logger = LoggerFactory.getLogger(SimpleProducer.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.64.128:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");//等待分区的所有副本应答，才表示此消息发送成功
        props.put(ProducerConfig.RETRIES_CONFIG, 0); //消息发送最大尝试次数
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);// 一批消息处理大小
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // 请求延时
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // 发送缓存区内存大小
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key序列化
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value序列化

        // 指定  partitioner 类
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 50; i++) {
            producer.send(new ProducerRecord<>("test", "luck", "luck dog---"));
        }
        Logger.info("finish");
        producer.close();
    }
}
