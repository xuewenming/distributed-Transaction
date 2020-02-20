package com.sam.seata.service;

/**
 * @author Mr.xuewenming
 * @title: IAccountInfoService
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1822:56
 */
public interface IAccountInfoService {

    void transfer(String accountNo, Double amount);
}
