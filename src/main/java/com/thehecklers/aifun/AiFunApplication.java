package com.thehecklers.aifun;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class AiFunApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiFunApplication.class, args);
    }

    @Bean
//	Function<String, Flux<String>> chat(AiFunService service) {
    Function<String, String> chat(AiFunService service) {
        return queryPrompt -> {
            try {
//				return service.getData(queryPrompt);
                return service.getData(queryPrompt).toStream()
                        .collect(Collectors.joining());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
//			return Flux.empty();
            return "";
        };
    }
}
