package com.example.kafka.service;

import reactor.core.publisher.Mono;

public interface MessageSender {
    Mono<Void> send();
}
