package com.sam.seata.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.xuewenming
 * @title: Bank2Client
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:16
 */
@Component
@FeignClient(value = "dtx-seata-bank2",fallback = Bank2ClientFallback.class,path = "/bank2")
public interface Bank2Client {

    @GetMapping("/bank2/transfer")
    String transfer(@RequestParam("accountNo") String accountNo, @RequestParam("amount") Double amount);


}
