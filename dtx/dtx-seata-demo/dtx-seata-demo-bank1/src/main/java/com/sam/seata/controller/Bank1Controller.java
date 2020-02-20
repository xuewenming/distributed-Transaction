package com.sam.seata.controller;

import com.sam.seata.service.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.xuewenming
 * @title: Bank1Controller
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:27
 */
@RestController
public class Bank1Controller {

    @Autowired
    IAccountInfoService accountInfoService;

    @GetMapping("/bank1/transfer")
    public String bank1_transfer(@RequestParam("accountNo") String accountNo,@RequestParam("amount") Double amount) {
        accountInfoService.transfer(accountNo,amount);
        return "success";
    }


}
