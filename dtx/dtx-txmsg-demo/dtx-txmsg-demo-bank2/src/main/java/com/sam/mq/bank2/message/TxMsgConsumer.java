package com.sam.mq.bank2.message;

import com.alibaba.fastjson.JSONObject;
import com.sam.mq.bank2.model.AccountChangeEvent;
import com.sam.mq.bank2.service.IAccountBank2Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: TxMsgConsumer
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2013:59
 */

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_txmsg_bank2",topic = "topic_txmsg")
public class TxMsgConsumer implements RocketMQListener<String> {

    @Autowired
    IAccountBank2Service accountBank2Service;


    @Override
    public void onMessage(String s) {

        log.info("bank2 begin consumer message ... s:{}",s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        String change = jsonObject.getString("change");
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(change, AccountChangeEvent.class);
        accountChangeEvent.setAccountNo("2");
        accountBank2Service.updateBanlance(accountChangeEvent);

    }
}
