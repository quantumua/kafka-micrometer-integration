package com.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.kafka.KafkaConfig.TOPIC;


@Slf4j
@RestController
@RequestMapping("/hello")
public class Controller {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    private AtomicInteger counter = new AtomicInteger(0);

    @GetMapping
    public ResponseEntity hello(){
        kafkaTemplate.send(TOPIC, "hello message #" + counter.getAndIncrement());
        return ResponseEntity.ok().build();
    }
}
