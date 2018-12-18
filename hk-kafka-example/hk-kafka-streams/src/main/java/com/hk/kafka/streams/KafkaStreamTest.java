package com.hk.kafka.streams;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

import java.util.Properties;

/**
 * @author kevin
 * @date 2018-09-05 15:38
 */
public class KafkaStreamTest {

    public static void main(String[] args) {
        String fromTopic = "streams_first";

        String toTopic = "streams_second";

        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.64.128:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "logFilter");

        StreamsConfig config = new StreamsConfig(properties);

        Topology topology = new Topology();
        topology.addSource("SOURCE", fromTopic)
                .addProcessor("PROCESS", (ProcessorSupplier<byte[], byte[]>) LoggerProcessor::new, "SOURCE")
                .addSink("SINK", toTopic, "PROCESS");
        new KafkaStreams(topology, config).start();
    }

    /**
     * 分析处理器
     */
    static class LoggerProcessor extends AbstractProcessor<byte[], byte[]> {

        @Override
        public void process(byte[] key, byte[] value) {
            String input = new String(value);
            System.out.println("---------------------> input :" + input);
            // 如果包含“>>>”则只保留该标记后面的内容
            if (input.contains(">>>")) {
                input = input.split(">>>")[1].trim();
            }
            // 输出到下一个topic
            context().forward("logProcessor".getBytes(), input.getBytes());
        }

    }
}
