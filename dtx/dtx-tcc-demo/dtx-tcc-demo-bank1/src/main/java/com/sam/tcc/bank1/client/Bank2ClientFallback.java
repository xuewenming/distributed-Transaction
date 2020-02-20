package com.sam.tcc.bank1.client;

import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: Bank2ClientFallback
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1916:45
 */
@Component
public class Bank2ClientFallback implements Bank2Client {


    @Override
    public String transfer(Double amount, String transId) {
        return "fail";
    }
}
