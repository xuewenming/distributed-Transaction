package com.sam.seata.bank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class DtxSeataDemoBank2Application {

    public static void main(String[] args) {
        SpringApplication.run(DtxSeataDemoBank2Application.class, args);
    }

}
