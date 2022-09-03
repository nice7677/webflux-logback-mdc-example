package kr.springboot.mdc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class MdcTestService {

    public Mono<Object> getJsonPlaceHolderTodo(String no) {

        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos/" + no)
                .build();

        Object response = webClient.get()
                .retrieve()
                .bodyToMono(Object.class);

        log.info(response.toString());

        return Mono.just(response);

    }

}
