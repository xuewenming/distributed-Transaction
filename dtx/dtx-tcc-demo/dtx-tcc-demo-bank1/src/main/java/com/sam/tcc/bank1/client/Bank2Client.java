package com.sam.tcc.bank1.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.xuewenming
 * @title: Bank2Client
 * @projectName distributed-Transaction
 * @description:
 * @date 2020/2/1916:43
 */
@Component
@FeignClient(value = "dtx-tcc-demo-bank2",path = "/bank2",fallback = Bank2ClientFallback.class)
public interface Bank2Client {

    @Hmily
    @GetMapping("/bank2/transfer")
    String transfer(@RequestParam("amount") Double amount,@RequestParam("transId") String transId);

}
