package com.sam.mq.bank2.service;

import com.sam.mq.bank2.model.AccountChangeEvent;

/**
 * @author Mr.xuewenming
 * @title: IAccountBank2Service
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2013:52
 */
public interface IAccountBank2Service {

    void updateBanlance(AccountChangeEvent accountChangeEvent);

}
