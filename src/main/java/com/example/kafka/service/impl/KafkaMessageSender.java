package com.example.kafka.service.impl;

import com.example.kafka.service.MessageSender;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.kafka.config.KafkaConfig.TOPIC;

@RequiredArgsConstructor
@Component
public class KafkaMessageSender implements MessageSender {
    private final KafkaTemplate<?, String> kafkaTemplate;
    private AtomicInteger counter = new AtomicInteger(0);
    private Timer messageSendTimer;

    @Override
    @Timed(value = "message-sent-timers", description = "Time spent sending message, annotated timer", extraTags = {"type", "annotated"})
    public void send() {
        messageSendTimer.record(() -> kafkaTemplate.send(TOPIC, "hello message #" + counter.getAndIncrement()));
    }

    @Autowired
    private void initTimer(MeterRegistry meterRegistry) {
        messageSendTimer = Timer.builder("message-sent-timers").tag("type", "custom").description("Time spent sending message, custom timer").register(meterRegistry);
    }
}
