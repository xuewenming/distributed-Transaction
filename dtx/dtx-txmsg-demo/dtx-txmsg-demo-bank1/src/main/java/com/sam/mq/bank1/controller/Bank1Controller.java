package com.sam.mq.bank1.controller;

import com.sam.mq.bank1.model.AccountChangeEvent;
import com.sam.mq.bank1.service.IAccountBank1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Mr.xuewenming
 * @title: Bank1Controller
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2014:13
 */
@RestController
public class Bank1Controller {

    @Autowired
    private IAccountBank1Service accountBank1Service;


    @GetMapping("/transfer")
    public String bank1_transfer(@RequestParam("amount") Double amount) {
        AccountChangeEvent event = new AccountChangeEvent();
        event.setTxNo(UUID.randomUUID().toString());
        event.setAccountNo("1");
        event.setAmount(amount);
        accountBank1Service.sendUpdateAccountBalance(event);
        return "success";
    }


}
