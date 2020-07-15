package edu.ncu.gateway;

import edu.ncu.gateway.config.AuthPathProperties;
import edu.ncu.gateway.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableZuulProxy
@EnableConfigurationProperties(AuthPathProperties.class)
//允许使用配置类
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
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
