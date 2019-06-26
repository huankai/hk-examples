package com.hk.kafka.producer.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author kevin
 * @date 2018-08-30 15:58
 */
@Slf4j
public class SimpleProducer {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "sjq-01:9092,sjq-02:9092,sjq-03:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");//等待分区的所有副本应答，才表示此消息发送成功
        props.put(ProducerConfig.RETRIES_CONFIG, 5); // 生产者从服务器收到临时性错误时，生产者重发消息的次数
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);// 一批消息处理大小
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // 请求延时
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // 发送缓存区内存大小
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key序列化
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value序列化

        // 指定  partitioner 类
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        boolean running = true;
        int index = 1;
        while (running) {
            Thread.sleep(800);
            String message = "Send message " + (index++);
            log.info("Send message {}...", message);
            producer.send(new ProducerRecord<>("test3", "luck", message), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        log.error("--------------------->" + exception.getMessage());
                    } else {
                        log.info("Send success ,partition is {},", metadata.partition());
                    }
                }
            });
//            producer.send(new ProducerRecord<>("test3", "luck", message));
        }
        log.info("finish");
        producer.close();
    }
}
