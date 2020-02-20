package com.sam.notify.pay.service;

import com.sam.notify.pay.pojo.AccountPay;

/**
 * @author Mr.xuewenming
 * @title: IPayService
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2015:42
 */
public interface IPayService {

    // 转账
    void transfer(AccountPay accountPay);

    // 查询转账后消息
    AccountPay payResult(AccountPay accountPay);

}
