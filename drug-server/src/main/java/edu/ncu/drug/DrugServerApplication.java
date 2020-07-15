package edu.ncu.drug;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //将自己注册到eureka-server
public class DrugServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrugServerApplication.class,args);
    }
}
