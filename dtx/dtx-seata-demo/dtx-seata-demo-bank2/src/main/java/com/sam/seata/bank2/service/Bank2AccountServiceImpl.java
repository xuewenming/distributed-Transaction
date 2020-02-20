package com.sam.seata.bank2.service;

import com.sam.seata.bank2.dao.AccountInfoDao;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.xuewenming
 * @title: Bank2AccountServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:40
 */
@Service
@Slf4j
public class Bank2AccountServiceImpl implements IBank2AccountService{

    @Autowired
    AccountInfoDao accountInfoDao;

    @Transactional
    @Override
    public void transfer(String accountNo, Double amount) {
        log.info("bank2 begin transfer XID={},amount={}"+ RootContext.getXID(),amount);
        accountInfoDao.updateTransfer(accountNo,amount);

        // 测试异常信息
        if (amount == 2) {
            throw new IllegalArgumentException("bank2 人为制造异常，amount={}" + 2);
        }

    }
}
