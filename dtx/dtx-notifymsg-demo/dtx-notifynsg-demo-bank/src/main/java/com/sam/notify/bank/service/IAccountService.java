package com.sam.notify.bank.service;

import com.sam.notify.bank.pojo.AccountChangeEvent;
import com.sam.notify.bank.pojo.AccountPay;

/**
 * @author Mr.xuewenming
 * @title: IAccountService
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2016:19
 */
public interface IAccountService  {

    // 更新账户余额
    void updateBanlance(AccountChangeEvent accountChangeEvent);


    // 主动查询充值结果
    public AccountPay queryPayResult(String txNo);

}
