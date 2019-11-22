package com.hk.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费者接受批量消息
 *
 * @author kevin
 * @date 2019-11-22 16:27
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "batch-topic", consumerGroup = "batch-group")
public class BatchConsumerListener implements RocketMQListener<String> {


    /**
     * 结果是一个 json 字符串，格式如下:
     * ---------------------------- 华 丽 的 分 割 线 -----------------------------------
     * <pre>
     * [{
     * "payload": "Hello RocketMQ Batch Msg#0",
     * "headers": {
     * "KEYS": "KEY_0",
     * "id": "9a83a260-6fbf-a1ce-f4b9-38088a36ba60",
     * "timestamp": 1574412534359
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#1",
     * "headers": {
     * "KEYS": "KEY_1",
     * "id": "43b8c906-313d-c997-2c69-9e68875e4d4f",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#2",
     * "headers": {
     * "KEYS": "KEY_2",
     * "id": "0dbac18f-c57c-69ca-d14d-a0e56c8a7c99",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#3",
     * "headers": {
     * "KEYS": "KEY_3",
     * "id": "3e5371ae-487a-14be-4578-612331d5ce74",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#4",
     * "headers": {
     * "KEYS": "KEY_4",
     * "id": "0da35dea-a182-8b8f-d57a-ffdfa039b37f",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#5",
     * "headers": {
     * "KEYS": "KEY_5",
     * "id": "e12d56f4-9dd1-80cc-0d17-470a18d79761",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#6",
     * "headers": {
     * "KEYS": "KEY_6",
     * "id": "be3cd514-5a6e-529f-47ec-75268e3eaafc",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#7",
     * "headers": {
     * "KEYS": "KEY_7",
     * "id": "fc3fc297-e9e9-6b6b-85fb-4facd40be130",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#8",
     * "headers": {
     * "KEYS": "KEY_8",
     * "id": "3929ebb9-2fe4-b275-8014-e9761d4f2f53",
     * "timestamp": 1574412534360
     * }
     * }, {
     * "payload": "Hello RocketMQ Batch Msg#9",
     * "headers": {
     * "KEYS": "KEY_9",
     * "id": "90f06458-0b7b-3c2c-0080-effbc08ac6aa",
     * "timestamp": 1574412534360
     * }
     * }]
     * </pre>
     *
     * @param message
     */
    @Override
    public void onMessage(String message) {
        log.debug("receive batch Message:{}", message);
    }
}
