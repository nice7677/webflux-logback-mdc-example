package kr.springboot.mdc.filter;

import org.slf4j.MDC;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.UUID;

public class MdcLoggingFilter implements WebFilter {

    private static final String MDC_UUID = "uuid";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).contextWrite(context -> {
            final String uuid = UUID.randomUUID().toString();
            MDC.put(MDC_UUID, uuid);
            return Context.of(MDC_UUID, uuid);
        });
    }
}
