package com.sam.notify.bank.client;

import com.sam.notify.bank.pojo.AccountPay;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: PayClientFallback
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2016:41
 */
@Component
public class PayClientFallback implements PayClient {

    @Override
    public AccountPay pay_result(String txid) {
        AccountPay accountPay = new AccountPay();
        accountPay.setResult("fail");
        return accountPay;
    }
}
