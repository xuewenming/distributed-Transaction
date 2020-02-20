package com.sam.mq.bank2.service.impl;

import com.sam.mq.bank2.dao.AccountInfoBank2Dao;
import com.sam.mq.bank2.model.AccountChangeEvent;
import com.sam.mq.bank2.service.IAccountBank2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.xuewenming
 * @title: AccountBank2ServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2013:53
 */
@Service
@Slf4j
public class AccountBank2ServiceImpl implements IAccountBank2Service {

    @Autowired
    AccountInfoBank2Dao accountInfoBank2Dao;

    @Transactional
    @Override
    public void updateBanlance(AccountChangeEvent accountChangeEvent) {
        log.info("bank2 begin transfer ... txID:{}", accountChangeEvent.getTxNo());

        // 进行幂等性校验
        String txNo = accountChangeEvent.getTxNo();
        int exitTx = accountInfoBank2Dao.isExitTx(txNo);
        if (exitTx > 0) {
            log.info("bank2 transfer already exec ... txNo:{}",txNo);
            return;
        }

        accountInfoBank2Dao.updateAccountBalance(accountChangeEvent.getAccountNo(), accountChangeEvent.getAmount());

        // 幂等性操作
        accountInfoBank2Dao.addTx(txNo);
    }
}
