package com.sam.seata.bank2.service;

/**
 * @author Mr.xuewenming
 * @title: IBank2AccountService
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:39
 */
public interface IBank2AccountService {

    void transfer(String accountNo, Double amount);

}
