package com.sam.seata.bank2.controller;

import com.sam.seata.bank2.service.IBank2AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.xuewenming
 * @title: Bank2TransferController
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:49
 */
@RestController
public class Bank2TransferController {
    @Autowired
    IBank2AccountService accountService;

    @GetMapping("/bank2/transfer")
    public String transfer(@RequestParam("accountNo") String accountNo, @RequestParam("amount") Double amount) {
        accountService.transfer(accountNo,amount);
        return "success";
    }

}
