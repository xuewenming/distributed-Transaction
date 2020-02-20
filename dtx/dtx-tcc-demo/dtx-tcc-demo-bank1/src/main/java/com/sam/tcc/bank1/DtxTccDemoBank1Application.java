package com.sam.tcc.bank1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"com.sam.tcc.bank1.client"})
@SpringBootApplication
@ComponentScan({"org.dromara.hmily","com.sam.tcc.bank1"})
public class DtxTccDemoBank1Application {

    public static void main(String[] args) {
        SpringApplication.run(DtxTccDemoBank1Application.class, args);
    }

}
