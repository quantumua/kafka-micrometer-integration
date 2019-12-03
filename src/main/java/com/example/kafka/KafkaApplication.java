package com.example.kafka;

import io.micrometer.core.instrument.binder.kafka.KafkaConsumerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class KafkaApplication/* implements CommandLineRunner */ {


    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args)/*.close()*/;
    }

  /*  @Autowired
    private KafkaTemplate<String, String> template;

    @Bean
    KafkaConsumerMetrics kafkaConsumerMetrics(){
        return new KafkaConsumerMetrics();
    }

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("TOPIC")
                .partitions(1)
                .replicas(2)
                .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(TimeUnit.MINUTES.toMillis(3)))
                .build();
    }

   *//* @Bean
    public NewTopic updateTopic() {
        return new NewTopic(TOPIC, 1, (short) 2).configs(updateTopicConfig());
    }*//*

    private Map<String, String> updateTopicConfig() {
        Map<String, String> topicConfig = new HashMap<>();
        topicConfig.put(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(TimeUnit.MINUTES.toMillis(3)));
        topicConfig.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        return topicConfig;
    }

    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        this.template.send(TOPIC, "foo1");
        this.template.send(TOPIC, "foo2");
        this.template.send(TOPIC, "foo3");
        latch.await(30, TimeUnit.SECONDS);
        log.info("All received");
    }

    @KafkaListener(groupId = "test", topics = TOPIC)
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        log.info(cr.toString());
        latch.countDown();
    }*/


}
