package com.sam.notify.bank.message;

import com.sam.notify.bank.pojo.AccountChangeEvent;
import com.sam.notify.bank.pojo.AccountPay;
import com.sam.notify.bank.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: NotifyMsgListener
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2016:50
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_notifymsg_bank1",topic = "topic_notifymsg")
public class NotifyMsgListener implements RocketMQListener<AccountPay> {

    @Autowired
    IAccountService accountService;

    @Override
    public void onMessage(AccountPay accountPay) {
        String result = accountPay.getResult();
        if ("success".equals(result)) {
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(accountPay.getAccountNo());//账号
            accountChangeEvent.setAmount(accountPay.getPayAmount());//金额
            accountChangeEvent.setTxNo(accountPay.getId());//充值事务号
            accountService.updateBanlance(accountChangeEvent);
        }
    }
}
