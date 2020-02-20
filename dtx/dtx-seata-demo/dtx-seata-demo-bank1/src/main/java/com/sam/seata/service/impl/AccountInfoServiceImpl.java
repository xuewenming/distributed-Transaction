package com.sam.seata.service.impl;

import com.sam.seata.client.Bank2Client;
import com.sam.seata.dao.AccountInfoDao;
import com.sam.seata.service.IAccountInfoService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.xuewenming
 * @title: AccountInfoServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1822:58
 */
@Service
@Slf4j
public class AccountInfoServiceImpl implements IAccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;
    @Autowired
    Bank2Client bank2Client;

    @Transactional
    @GlobalTransactional // 开启全局事务
    @Override
    public void transfer(String accountNo, Double amount) {
        // 获取全局事务ID RootContext.getXID();
        log.info("bank1 begin transfer accountNo={},amount={}, XID={}",accountNo,amount, RootContext.getXID());
        accountInfoDao.updateAccountBalance(accountNo, amount * -1);

        // 调用转账李四服务
        String transferAccountNo = "2";
        String transfer = bank2Client.transfer(transferAccountNo, amount);
        if ("fail".equals(transfer)) {
            throw new RuntimeException("bank1 transfer is fail,XID={}" + RootContext.getXID());
        }

        // 人为制造异常
        if (amount == 3) {
            throw new RuntimeException("bank1人为制造异常,转账金额={}" + 3);
        }
    }
}
