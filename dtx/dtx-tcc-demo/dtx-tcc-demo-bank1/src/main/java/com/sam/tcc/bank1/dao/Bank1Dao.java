package com.sam.tcc.bank1.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: Bank1Dao
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1916:52
 */
@Component
@Mapper
public interface Bank1Dao {

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>业务操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.

    // 减少余额
    @Update("update account_info set account_balance=account_balance - #{amount} where account_balance>=#{amount} and account_no=#{accountNo}")
    int subtractAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);
    // 增加余额
    @Update("update account_info set account_balance=account_balance + #{amount} where account_no=#{accountNo} ")
    int addAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>新增事务记录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.

    // try
    @Insert("insert into local_try_log values(#{txNo},now());")
    int addTry(String localTradeNo);
    // confirm
    @Insert("insert into local_confirm_log values(#{txNo},now());")
    int addConfirm(String localTradeNo);
    // cancel
    @Insert("insert into local_cancel_log values(#{txNo},now());")
    int addCancel(String localTradeNo);


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>查询事务记录-幂等性判断>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.

    /**
     * 查询分支事务try是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_try_log where tx_no = #{txNo} ")
    int isExistTry(String localTradeNo);
    /**
     * 查询分支事务confirm是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_confirm_log where tx_no = #{txNo} ")
    int isExistConfirm(String localTradeNo);

    /**
     * 查询分支事务cancel是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_cancel_log where tx_no = #{txNo} ")
    int isExistCancel(String localTradeNo);

}
