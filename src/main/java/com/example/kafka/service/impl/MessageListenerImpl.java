package com.example.kafka.service.impl;

import com.example.kafka.config.KafkaConfig;
import com.example.kafka.service.MessageListener;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
class MessageListenerImpl implements MessageListener {
    private final MeterRegistry meterRegistry;
    private Random processingTime = new Random();

    @KafkaListener(groupId = "test", topics = KafkaConfig.TOPIC)
    public void listen(ConsumerRecord<?, ?> cr) {
        Timer.Sample sample = Timer.start(meterRegistry);
        handleRecord(cr);
        sample.stop(meterRegistry.timer("message-processing-time", "listener", "kafka"));
    }

    @SneakyThrows
    private void handleRecord(ConsumerRecord<?, ?> cr) {
        TimeUnit.MILLISECONDS.sleep(processingTime.nextInt(50));
        log.info(cr.toString());
    }
}
