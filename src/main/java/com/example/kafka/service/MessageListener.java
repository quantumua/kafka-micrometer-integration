package com.example.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface MessageListener {
    void listen(ConsumerRecord<?, ?> cr);
}
