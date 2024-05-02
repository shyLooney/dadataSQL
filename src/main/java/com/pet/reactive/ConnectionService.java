package com.pet.reactive;

import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class ConnectionService {
    @GetMapping
    public String index(Model model) {

        RouterFunction<?> routerFunction = RouterFunctions.route(RequestPredicates.GET(""),
                request -> ServerResponse.ok().body(Mono.just("Hello World!"), String.class));
        System.out.println(routerFunction);
        return "index";
    }
}
