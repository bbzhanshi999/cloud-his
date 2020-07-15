package edu.ncu.doctor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient //将自己注册到eureka-server
@EnableFeignClients //声明使用open feign客户端
@MapperScan("edu.ncu.doctor.dao")
@EnableTransactionManagement //自动装配事物管理器
public class DoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class,args);
    }
}
