package com.sam.notify.bank.controller;

import com.sam.notify.bank.pojo.AccountPay;
import com.sam.notify.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.xuewenming
 * @title: Bank1Controller
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2016:53
 */
@RestController
public class Bank1Controller
{
    @Autowired
    private IAccountService accountInfoService;

    //主动查询充值结果
    @GetMapping(value = "/payresult/{txNo}")
    public AccountPay result(@PathVariable("txNo") String txNo){
        AccountPay accountPay = accountInfoService.queryPayResult(txNo);
        return accountPay;
    }
}
