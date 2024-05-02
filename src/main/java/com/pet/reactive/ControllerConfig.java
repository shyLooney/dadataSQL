package com.pet.reactive;

import com.pet.reactive.entity.SuggestionList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class ControllerConfig {

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/hello"),
                request -> ServerResponse.ok().body(Mono.just("Hello World!"), String.class));
    }

    @Bean
    public CommandLineRunner webClientTest() {
        return args -> {
            var resp = WebClient.create("http://suggestions.dadata.ru")
                    .post()
                    .uri("/suggestions/api/4_1/rs/findById/address")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                    .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                    .bodyValue("{ \"query\": \"9120b43f-2fae-4838-a144-85e43c2bfb29\" }")
                    .retrieve()
                    .bodyToMono(SuggestionList.class);

            resp.subscribe(i -> System.out.println("Response: " + i));
            return;
        };
    }
}
