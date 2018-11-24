package com.hk.kafka.producer.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 有回调方法的生产者
 *
 * @author: kevin
 * @date: 2018-08-30 15:58
 */
public class CallbackProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackProducer.class);

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
                LOGGER.info("partition : {},offset:{},topic:{}", metadata.partition(), metadata.offset(), metadata.topic());
            }
        });
        LOGGER.info("finish");
        producer.close();
    }
}
