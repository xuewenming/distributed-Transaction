package com.sam.notify.pay.controller;

import com.sam.notify.pay.pojo.AccountPay;
import com.sam.notify.pay.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Mr.xuewenming
 * @title: PayController
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2015:53
 */
@RestController
public class PayController {


    @Autowired
    IPayService payService;

    @GetMapping("/pay")
    public String transfer() {
        AccountPay accountPay = new AccountPay();
        accountPay.setId(UUID.randomUUID().toString());
        accountPay.setAccountNo("1");
        accountPay.setPayAmount(1);

        payService.transfer(accountPay);

        return "success";
    }


    @GetMapping("/result")
    public AccountPay pay_result(@RequestParam("txNo") String txid) {
        AccountPay accountPay = new AccountPay();
        accountPay.setId(txid);

        AccountPay pay = payService.payResult(accountPay);
        return pay;
    }



}
