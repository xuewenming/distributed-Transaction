package com.sam.notify.bank.client;

import com.sam.notify.bank.pojo.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.xuewenming
 * @title: PayClient
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2016:40
 */
@Component
@FeignClient(value = "dtx-notifymsg-demo-pay",path = "/pay",fallback = PayClientFallback.class)
public interface PayClient {

    @GetMapping("/result")
    public AccountPay pay_result(@RequestParam("txNo") String txid);
}
