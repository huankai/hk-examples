package consumer;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author huangkai
 * @date 2018-11-10 14:53
 */
@SpringBootApplication
@EnableBinding(Sink.class)
public class Consumer02Application {

    private Logger logger = LoggerFactory.getLogger(Consumer02Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Consumer02Application.class, args);
    }


    @StreamListener(Sink.INPUT)
    public void transactionMessage(List<MessageVo> messages) {
        logger.info("consumer02 --------> {}", messages);
    }

    @Data
    private static class MessageVo {

        private String id;

        private String name;

        private String title;

        private LocalDateTime date;
    }

}
