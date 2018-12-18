package com.hk.kafka.producer.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * kafka 管理测试
 *
 * @author kevin
 * @date 2018-09-05 13:52
 */
public class KafkaAdminClientTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.64.128:9092,192.168.64.129:9092");
        AdminClient adminClient = KafkaAdminClient.create(props);
        ListTopicsResult topicsResult = adminClient.listTopics(); //获取所有topic
        System.out.println(topicsResult.names().get());
    }
}
