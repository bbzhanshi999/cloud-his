package edu.ncu.out;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient //将自己注册到eureka-server
public class OutServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OutServerApplication.class,args);
    }
}
