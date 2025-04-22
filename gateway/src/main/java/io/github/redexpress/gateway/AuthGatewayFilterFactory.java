package io.github.redexpress.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config>{

    public AuthGatewayFilterFactory() {
        super(AuthGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        GatewayFilter filter = new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders headers = request.getHeaders();
                String token = headers.getFirst("Authorization");
                if (!StringUtils.hasText(token)) {
                    return chain.filter(exchange);
                }
                ServerHttpResponse response = exchange.getResponse();
                if (!token.equals("Bearer OK")) {
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap("auth failed".getBytes());
                    return response.writeWith(Flux.just(buffer));
                }
                return chain.filter(exchange);
            }
        };
        return filter;
    }

    public static class Config {
    }
}
