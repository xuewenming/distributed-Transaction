package com.sam.seata.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: AccountInfoDao
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1822:53
 */
@Mapper
@Component
public interface AccountInfoDao {


    //   int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double 
    //        amount);
    //update account_info set account_balance = account_balance + #{amount} where 
    //account_no = #{accountNo}

    @Update("UPDATE account_info SET account_balance = account_balance + -1 WHERE account_no = '1'")
    int updateAccountBalance(@Param("accountNo") String accountNo,@Param("amount") Double amount);


}
