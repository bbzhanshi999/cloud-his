package edu.ncu.newgateway.filters;

import com.nimbusds.jose.JOSEException;
import edu.ncu.newgateway.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class JwtLoginFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            if(Objects.requireNonNull(exchange.getResponse().getStatusCode()).is2xxSuccessful()){
                String username = exchange.getResponse().getHeaders().getFirst("username");
                String sub = exchange.getResponse().getHeaders().getFirst("eid");
                try {
                    String token = JwtUtil.genToken(sub, username);
                    exchange.getResponse().getHeaders().set("access-token",token);
                } catch (JOSEException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
