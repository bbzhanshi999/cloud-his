package edu.ncu.newgateway.filters;

import com.nimbusds.jose.JOSEException;
import edu.ncu.commons.model.EmployeeInfo;
import edu.ncu.newgateway.utils.JwtException;
import edu.ncu.newgateway.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

public class JwtTokenFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取token
        String token = exchange.getRequest().getHeaders().getFirst("access-token");
        try {
            EmployeeInfo employeeInfo = JwtUtil.validToken(token);

            return chain.filter(exchange.mutate()
                    .request(exchange.getRequest().mutate()
                            .header("username", employeeInfo.getUsername())
                            .header("eid", employeeInfo.getId()).build())
                    .build()).then(Mono.fromRunnable(() -> {
                        try {
                            String newToken = JwtUtil.genToken(employeeInfo.getId(), employeeInfo.getUsername());
                            exchange.getResponse().getHeaders().set("access-token",newToken);
                        } catch (JOSEException e) {
                            e.printStackTrace();
                        }
                    }
            ));
        } catch (ParseException | JOSEException | JwtException e) {
            e.printStackTrace();
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
