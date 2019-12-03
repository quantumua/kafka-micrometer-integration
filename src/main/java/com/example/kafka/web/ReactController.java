package com.example.kafka.web;

import com.example.kafka.service.MessageSender;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Configuration
@AllArgsConstructor
public class ReactController {
    MessageSender messageSender;

    @Bean
    RouterFunction<ServerResponse> greetingRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/hello"),
                req -> ServerResponse.ok().body(messageSender.send(), Mono.class));
    }
}
