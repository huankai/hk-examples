package com.hk.kafka.producer.example;

import com.hk.commons.util.JsonUtils;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 生产者拦截器
 *
 * @author kevin
 * @date 2018-09-05 15:17
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CounterInterceptor.class);

    private int errorCount = 0;

    private int successCount = 0;

    /**
     * 发送之前调用
     *
     * @param record record
     * @return record
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    /**
     * 确认offset 执行
     *
     * @param metadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) { // 没有异常信息，表示消息成功发送到 kafka
            successCount++;
        } else { // 有异常信息，表示消息不能确定是否发送到 kafka
            errorCount++;
        }
    }

    /**
     * 关闭资源
     */
    @Override
    public void close() {
        LOGGER.debug("successCount : {},errorCount :{}", successCount, errorCount);
    }

    /**
     * 配置信息
     *
     * @param configs configs
     */
    @Override
    public void configure(Map<String, ?> configs) {
        LOGGER.debug(JsonUtils.serialize(configs));

    }
}
