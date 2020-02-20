package com.sam.tcc.bank2.controller;

import com.sam.tcc.bank2.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.xuewenming
 * @title: Bank2Controller
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1918:18
 */
@RestController
public class Bank2Controller {

    @Autowired
    IBankService bankService;

    @GetMapping("/bank2/transfer")
    public String transfer(@RequestParam("amount") Double amount, @RequestParam("transId") String transId) {
        bankService.transfer(transId,amount);
        return "success";
    }


}
