package com.sam.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.sam.seata.client"})
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
public class DtxSeataDemoBank1Application {

    public static void main(String[] args) {
        SpringApplication.run(DtxSeataDemoBank1Application.class, args);
    }

}
