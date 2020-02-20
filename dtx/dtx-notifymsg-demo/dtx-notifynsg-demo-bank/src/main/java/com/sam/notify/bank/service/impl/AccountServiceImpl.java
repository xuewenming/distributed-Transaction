package com.sam.notify.bank.service.impl;

import com.sam.notify.bank.client.PayClient;
import com.sam.notify.bank.dao.BankDao;
import com.sam.notify.bank.pojo.AccountChangeEvent;
import com.sam.notify.bank.pojo.AccountPay;
import com.sam.notify.bank.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.xuewenming
 * @title: AccountServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2016:29
 */
@Service
@Slf4j
public class AccountServiceImpl implements IAccountService {

    @Autowired
    BankDao bankDao;
    @Autowired
    PayClient payClient;

    @Override
    public void updateBanlance(AccountChangeEvent accountChangeEvent) {
        String txNo = accountChangeEvent.getTxNo();

        //幂等性判断
        int existTx = bankDao.isExistTx(txNo);
        if (existTx > 0) {
            log.info("bank1 幂等判断失败...");
            return;
        }

        bankDao.updateAccountBalance(accountChangeEvent.getAccountNo(), accountChangeEvent.getAmount());

        // 用于幂等性判断
        bankDao.addTx(txNo);
    }




    @Override
    public AccountPay queryPayResult(String txNo) {
        AccountPay accountPay = payClient.pay_result(txNo);
        String result = accountPay.getResult();
        if ("success".equals(result)) {
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(accountPay.getAccountNo());//账号
            accountChangeEvent.setAmount(accountPay.getPayAmount());//金额
            accountChangeEvent.setTxNo(accountPay.getId());//充值事务号
            updateBanlance(accountChangeEvent);
        }
        return accountPay;
    }
}
