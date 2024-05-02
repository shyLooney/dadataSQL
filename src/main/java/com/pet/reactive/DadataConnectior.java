package com.pet.reactive;

import com.pet.reactive.entity.SuggestionList;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class DadataConnectior {
    static public Mono<SuggestionList> getData(String query) {
        return WebClient.create("http://suggestions.dadata.ru")
                .post()
                .uri("/suggestions/api/4_1/rs/findById/address")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .bodyValue("{ \"query\": \"" + query + "\" }")
                .retrieve()
                .bodyToMono(SuggestionList.class);
    }
}
