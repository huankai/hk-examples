package com.hk.kafka.producer.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * 拦截器 Producer
 *
 * @author: kevin
 * @date: 2018-09-05 15:21
 */
public class InterceptorProducer {


    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(SimpleProducer.class);

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

        // 添加拦截器
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, Collections.singletonList(CounterInterceptor.class.getName()));

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>("test", "luck", "Interceptor Test..."));
        Logger.info("finish");
        producer.close(); // 必须调用 此方法，才会调用 {@link ProducerInterceptor#close} 方法
    }
}
