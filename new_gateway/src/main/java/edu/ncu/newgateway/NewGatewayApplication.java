package edu.ncu.newgateway;

import edu.ncu.newgateway.filters.JwtLoginFilter;
import edu.ncu.newgateway.filters.JwtTokenFilter;
import edu.ncu.newgateway.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NewGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-login", r -> r.path("/pub/login")
                        .filters(f -> f.stripPrefix(1).filter(new JwtLoginFilter()))
                        .uri("lb://public-server"))
                .route("doctor-server", r -> r.path("/doctor/**")
                        .filters(f -> f.stripPrefix(1).filter(new JwtTokenFilter()))
                        .uri("lb://doctor-server"))
                .route("public-server", r -> r.path("/pub/**")
                        .filters(f -> f.stripPrefix(1).filter(new JwtTokenFilter()))
                        .uri("lb://public-server"))
                .build();


    }
    @Autowired
    private Environment env;

    @Bean
    @Lazy(false)
    public void initJwt(){
        JwtUtil.expire = Long.parseLong(env.getProperty("jwt.expire"));
        JwtUtil.secret = env.getProperty("jwt.secret");
    }
}
