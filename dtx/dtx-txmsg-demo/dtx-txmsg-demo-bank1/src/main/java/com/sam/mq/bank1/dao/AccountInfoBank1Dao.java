package com.sam.mq.bank1.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: AccountInfoBank1Dao
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2012:10
 */
@Component
@Mapper
public interface AccountInfoBank1Dao {

    // 更新余额
    @Update("update account_info set account_balance=account_balance - #{amount} where account_no=#{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo,@Param("amount") Double amount);


    // 是否存在事务ID
    @Select("select count(1) from de_duplication where tx_no = #{txNo}")
    int isExitTx(@Param("txNo") String txNo);


    // 添加新事务ID
    @Insert("insert into de_duplication values(#{txNo},now())")
    int addTx(@Param("txNo") String txNo);


}
