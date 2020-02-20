package com.sam.tcc.bank2.service.impl;

import com.sam.tcc.bank2.dao.Bank2Dao;
import com.sam.tcc.bank2.service.IBankService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.xuewenming
 * @title: Bank2ServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1918:05
 */
@Slf4j
@Service
public class Bank2ServiceImpl implements IBankService {


    @Autowired
    Bank2Dao bank2Dao;


    @Hmily(confirmMethod = "commit",cancelMethod = "fallback")
    @Transactional
    @Override
    public void transfer(String transid, Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();

        log.info("bank2 begin transfer ... transid:{}",transId);
    }


    @Transactional
    public void commit(String transid,String accountNo,Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        accountNo = "2";

        // 判断幂等性
        int existConfirm = bank2Dao.isExistConfirm(transId);
        if (existConfirm > 0) {
            log.info("bank2 confirm 已经执行过，transferid:{}",transId);
            return;
        }

        // 业务操作
        bank2Dao.addAccountBalance(accountNo, amount);

        // 用于幂等性判断
        bank2Dao.addConfirm(transId);



    }


    @Transactional
    public void fallback(String transid, String accountNo, Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();

        log.info("bank2 begin cancel ... transid:{}",transId);
    }

}
