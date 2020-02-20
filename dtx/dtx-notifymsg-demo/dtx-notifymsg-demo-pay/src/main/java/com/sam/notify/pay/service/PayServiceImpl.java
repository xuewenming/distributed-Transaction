package com.sam.notify.pay.service;

import com.sam.notify.pay.dao.PayDao;
import com.sam.notify.pay.pojo.AccountPay;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.xuewenming
 * @title: PayServiceImpl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2015:44
 */
@Slf4j
@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    PayDao payDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void transfer(AccountPay accountPay) {
        // 插入转账记录
        payDao.insertAccountPay(accountPay.getId(),accountPay.getAccountNo(),accountPay.getPayAmount(),"success");

        accountPay.setResult("success");
        rocketMQTemplate.convertAndSend("topic_notifymsg",accountPay);
    }


    @Override
    public AccountPay payResult(AccountPay accountPay) {

        AccountPay pay = payDao.findByIdTxNo(accountPay.getId());
        log.info("pay result accountNo:{}", pay.getAccountNo());
        return pay;

    }
}
