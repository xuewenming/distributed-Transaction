package com.sam.mq.bank1.message;

import com.alibaba.fastjson.JSONObject;
import com.sam.mq.bank1.dao.AccountInfoBank1Dao;
import com.sam.mq.bank1.model.AccountChangeEvent;
import com.sam.mq.bank1.service.IAccountBank1Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xuewenming
 * @title: ProducerTxmsgListener
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2012:27
 */
@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "consumer_group_txmsg_bank1")
public class ProducerTxmsgListener implements RocketMQLocalTransactionListener {

    @Autowired
    IAccountBank1Service accountBank1Service;
    @Autowired
    AccountInfoBank1Dao accountInfoBank1Dao;


    // 事务消息发送后的回调方法，当消息发送给MQ成功，此方法被回调
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        String payload = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(payload);
        String accountChangeString = jsonObject.getString("change");
        AccountChangeEvent event = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);


        try {
            // 当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            accountBank1Service.uodateAccountBalance(event);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            // 当返回的是ROLLBACK,MQ将消息删除
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    // 事务回查，当Prodver事务执行成功
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // Producer端执行本地事务过程中，执行端挂掉，或超时。mq会询问获取事务执行的状态，判断消息是否投递

        String payload = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(payload);
        String accountChangeString = jsonObject.getString("change");
        AccountChangeEvent event = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);


        String txNo = event.getTxNo();

        int exitTx = accountInfoBank1Dao.isExitTx(txNo);
        if (exitTx > 0) {
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
