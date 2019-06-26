package com.hk.kafka.producer.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 有回调方法的生产者
 *
 * @author kevin
 * @date 2018-08-30 15:58
 */
@Slf4j
public class CallbackProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.64.128:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 指定  partitioner 类
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        // ProducerRecord 有多个构造器，可以指定  partition,如果指定  了partition ，key 可以不用指定
        producer.send(new ProducerRecord<>("test", 0, null, "luck  ddd"), (metadata, exception) -> {
            if (null != metadata) {
                log.info("partition : {},offset:{},topic:{}", metadata.partition(), metadata.offset(), metadata.topic());
            }
        });
        log.info("finish");
        producer.close();
    }
}
