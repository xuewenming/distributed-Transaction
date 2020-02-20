package com.sam.tcc.bank2.service;

/**
 * @author Mr.xuewenming
 * @title: IBankService
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1918:04
 */
public interface IBankService {


    void transfer(String transid,Double amount);

}
