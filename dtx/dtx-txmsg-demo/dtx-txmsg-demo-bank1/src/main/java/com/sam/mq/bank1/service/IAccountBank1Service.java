package com.sam.mq.bank1.service;

import com.sam.mq.bank1.model.AccountChangeEvent;

/**
 * @author Mr.xuewenming
 * @title: IAccountBank1Service
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2012:18
 */
public interface IAccountBank1Service {

    // 向MQ发送转账消息
    void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

    // 更新账户，扣减金额
    void uodateAccountBalance(AccountChangeEvent accountChangeEvent);

}
