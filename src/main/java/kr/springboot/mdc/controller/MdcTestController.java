package kr.springboot.mdc.controller;

import kr.springboot.mdc.service.MdcTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class MdcTestController {

    private final MdcTestService mdcTestService;

    @GetMapping("/{no}")
    public Mono<ResponseEntity> getJsonPlaceTodo(@PathVariable String no) {

        return mdcTestService.getJsonPlaceHolderTodo(no)
                .flatMap(todo -> Mono.just(ResponseEntity.ok(todo)))
                .cast(ResponseEntity.class)
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage())));

    }

}
