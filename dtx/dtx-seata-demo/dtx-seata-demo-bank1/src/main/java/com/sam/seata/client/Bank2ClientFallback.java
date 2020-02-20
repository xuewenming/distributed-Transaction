package com.sam.seata.client;

import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: Bank2ClientFallback
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:18
 */

@Component
public class Bank2ClientFallback implements Bank2Client {


    @Override
    public String transfer(String accountNo, Double amount) {
        return "fail";
    }
}
