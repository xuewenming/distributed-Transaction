package com.sam.tcc.bank1.service.impl;

import com.sam.tcc.bank1.client.Bank2Client;
import com.sam.tcc.bank1.dao.Bank1Dao;
import com.sam.tcc.bank1.service.IBank1AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.xuewenming
 * @title: Bank1ServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1916:42
 */
@Service
@Slf4j
public class Bank1ServiceImpl implements IBank1AccountService {

    @Autowired
    Bank1Dao bank1Dao;
    @Autowired
    Bank2Client bank2Client;

    /**
     * @description:
     *  try:
     *  判断幂等性
     *  判断悬挂
     *
     *  confirm:
     *  判断幂等性
     *
     *
     *  cancle:
     *  判断幂等性
     *  判断空回滚
     *
     */

    @Hmily(confirmMethod = "commit",cancelMethod = "rollback")
    @Transactional
    @Override
    public void transfer(Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("amount:{},transid:{}",amount,transId);


        // 幂等性校验
        int existTry = bank1Dao.isExistTry(transId);
        if (existTry > 0) {
            log.info("幂等性校验失败 - Try, transId = {}",transId);
            return;
        }

        // 悬挂校验
        int existConfirm = bank1Dao.isExistConfirm(transId);
        if (existConfirm > 0) {
            log.info("悬挂校验失败 - Confirm, transId = {}",transId);
            return;
        }
        int existCancel = bank1Dao.isExistCancel(transId);
        if (existCancel > 0) {
            log.info("悬挂校验失败 - Cancel, transId = {}",transId);
            return;
        }


        // 业务处理 (张三向李四转账)- 扣减金额
        int accountBalance = bank1Dao.subtractAccountBalance("1", amount);
        if (accountBalance <= 0) {
            throw new HmilyRuntimeException("bank1 exception: 扣减失败，事务id:{},账户信息:{}" + transId + "1");
        }

        // 增加本地事务Try成功记录，用于幂等性的控制
        bank1Dao.addTry(transId);

        // 远程调用 李四 接口
        String transfer = bank2Client.transfer(amount, transId);
        if ("fail".equals(transfer)) {
            throw new HmilyRuntimeException("远程调用李四接口失败,事务ID:{}" + transId);
        }

        // 测试bank1接口异常
        if (amount == 3) {
            throw new RuntimeException("bank1 make exception 3");
        }

    }

    // confirm
    @Transactional
    public void commit(String accountNo,Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        accountNo = "1";
        log.info("bank1 begin commit ... transId:{}",transId);
    }


    // cancel
    @Transactional
    public void rollback(String accountNo, Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        accountNo = "1";
        log.info("bank1 transaction fail...transId:{}" + transId);

        // 判断空回滚
        int aTry = bank1Dao.isExistTry(transId);
        if (aTry > 0) {
            log.info("判断空回滚...");
            return;
        }

        // 判断幂等性
        int existCancel = bank1Dao.isExistCancel(transId);
        if (existCancel > 0) {
            log.info("判断幂等性...");
            return;
        }

        // 将扣减金额放回账户
        bank1Dao.addAccountBalance(accountNo, amount);

        // 添加cancel日志，用于幂等性判断
        bank1Dao.addCancel(transId);
    }

}
