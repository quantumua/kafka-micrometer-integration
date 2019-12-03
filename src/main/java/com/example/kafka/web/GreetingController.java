package com.example.kafka;

import com.example.kafka.service.MessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/hello")
public class GreetingController {
    MessageSender messageSender;

    @GetMapping
    public ResponseEntity hello() {
        messageSender.send();
        return ResponseEntity.ok().build();
    }
}
