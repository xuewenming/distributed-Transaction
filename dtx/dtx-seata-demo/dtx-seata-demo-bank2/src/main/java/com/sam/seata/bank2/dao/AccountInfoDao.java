package com.sam.seata.bank2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: AccountInfoDao
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/199:42
 */
@Mapper
@Component
public interface AccountInfoDao {

    //"UPDATE account_info SET account_balance = account_balance + #{amount} WHERE 
    //account_no = #{accountNo}

    @Update("UPDATE account_info SET account_balance = account_balance + 1 WHERE account_no = '2'")
    void updateTransfer(@Param("accountNo") String accountNo, @Param("amount") Double amount);

}
