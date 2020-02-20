package com.sam.mq.bank1.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sam.mq.bank1.dao.AccountInfoBank1Dao;
import com.sam.mq.bank1.model.AccountChangeEvent;
import com.sam.mq.bank1.service.IAccountBank1Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.xuewenming
 * @title: AccountBank1Impl
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2012:19
 */
@Slf4j
@Service
public class AccountBank1Impl implements IAccountBank1Service {

    @Autowired
    AccountInfoBank1Dao accountInfoBank1Dao;
    @Autowired
    RocketMQTemplate rocketMQTemplate;


    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {

        // 将account转换成JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("change", accountChangeEvent);
        String jsonString = jsonObject.toJSONString();

        // 生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonString).build();

        // 发送一条事务消息
        /**
         * @description:
         * @param: txProducerGroup 生产组
         * @param:   destination topic
         * @param: message 消息
         * @arg: 参数
         */
        rocketMQTemplate.sendMessageInTransaction("consumer_group_txmsg_bank1", "topic_txmsg", message, null);



    }

    @Transactional
    @Override
    public void uodateAccountBalance(AccountChangeEvent accountChangeEvent) {
        // 判断幂等性
        String txNo = accountChangeEvent.getTxNo();
        int exitTx = accountInfoBank1Dao.isExitTx(txNo);
        if (exitTx > 0) {
            log.info("bank1 txNo already exist, txNo:{}",txNo);
            return;
        }
        accountInfoBank1Dao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());


        // 幂等性
        accountInfoBank1Dao.addTx(txNo);

        if (accountChangeEvent.getAmount() == 3) {
            throw new IllegalArgumentException("Bank1人为制造异常。。。");
        }

    }
}
