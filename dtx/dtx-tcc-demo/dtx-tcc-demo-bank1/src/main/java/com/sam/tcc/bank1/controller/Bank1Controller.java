package com.sam.tcc.bank1.controller;

import com.sam.tcc.bank1.service.IBank1AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.xuewenming
 * @title: Bank1Controller
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1916:35
 */

@RestController
public class Bank1Controller {

    @Autowired
    IBank1AccountService bank1AccountService;

    @GetMapping("/bank1/transfer")
    public String bank1_transfer(@RequestParam("amount") Double amount) {
        bank1AccountService.transfer(amount);
        return "success";
    }


}
