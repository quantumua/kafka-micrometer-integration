package com.example.kafka;

import io.micrometer.core.instrument.binder.kafka.KafkaConsumerMetrics;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class KafkaConfig {

    public static final String TOPIC = "test";

    @Bean
    KafkaConsumerMetrics kafkaConsumerMetrics() {
        return new KafkaConsumerMetrics();
    }

/*    @Bean
    LoggingMeterRegistry loggingMeterRegistry() {
        return new LoggingMeterRegistry();//by default, it will log metrics every 1m
    }*/

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC)
                .partitions(1)
                .replicas(2)
                .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(TimeUnit.MINUTES.toMillis(3)))
                .config(TopicConfig.SEGMENT_BYTES_CONFIG, "300000000")
                .build();
    }

    @Bean
    public MessageListener listener() {
        return new MessageListenerImpl();
    }

    interface MessageListener {
        void listen(ConsumerRecord<?, ?> cr);
    }

    static class MessageListenerImpl implements MessageListener {

        @KafkaListener(groupId = "test", topics = TOPIC)
        public void listen(ConsumerRecord<?, ?> cr) {
            log.info(cr.toString());
        }
    }
}
