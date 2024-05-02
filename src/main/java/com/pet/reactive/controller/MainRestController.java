package com.pet.reactive.controller;

import com.pet.reactive.dto.DadataTransferObject;
import com.pet.reactive.entity.Suggestion;
import com.pet.reactive.entity.SuggestionList;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainRestController {
    //    private final SuggestionRepository suggestionRepository;
//    private final DadataRepository dadataRepository;
    private final DadataTransferObject transferObject;

    @GetMapping
    public Mono<SuggestionList> test(@RequestParam String query) {
        Mono<SuggestionList> suggestion = WebClient.create("http://suggestions.dadata.ru")
                .post()
                .uri("/suggestions/api/4_1/rs/findById/address")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .bodyValue("{ \"query\": \"" + query + "\" }")
                .retrieve()
                .bodyToMono(SuggestionList.class);

        suggestion.subscribe(suggestionList -> {
                    Suggestion temp = suggestionList.getSuggestions().get(0);
                    transferObject.save(temp);
                });


        return suggestion;
    }

    @PostMapping("/test")
    public Mono<SuggestionList> testPost(@RequestBody String query) {
        Mono<SuggestionList> suggestion = WebClient.create("http://suggestions.dadata.ru")
                .post()
                .uri("/suggestions/api/4_1/rs/findById/address")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .bodyValue("{ \"query\": \"" + query + "\" }")
                .retrieve()
                .bodyToMono(SuggestionList.class);

        suggestion.subscribe(suggestionList -> {
                    Suggestion temp = suggestionList.getSuggestions().get(0);
                    transferObject.save(temp);
                });

        return suggestion;
    }



//    @GetMapping
//    public Mono<SuggestionList> findByFias(@RequestParam String fias) {
//        Mono<SuggestionList> suggestion = DadataConnectior.getData(fias);
//
//        suggestion.subscribe(i -> {
//            Suggestion temp = i.getSuggestions().get(0);
//            dadataRepository.save(temp.getData()).flatMap(elem -> {
//                temp.setDadataId(elem.getId());
//                return suggestionRepository.save(temp);
//            }).subscribe();
//        });
//
//        return suggestion;
//    }
//
//    @GetMapping("/db")
//    public Mono<DataDadata> test2() {
//        return dadataRepository.save(new DataDadata());
//    }
}
