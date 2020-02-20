package com.sam.mq.bank2.model;

/**
 * @author Mr.xuewenming
 * @title: AccountChangeEvent
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/2012:23
 */
public class AccountChangeEvent {

    /**
     * 账号
     */
    private String accountNo;
    /**
     * 变动金额
     */
    private double amount;
    /**
     * 事务号
     */
    private String txNo;


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTxNo() {
        return txNo;
    }

    public void setTxNo(String txNo) {
        this.txNo = txNo;
    }

    @Override
    public String toString() {
        return "AccountChangeEvent{" +
                "accountNo='" + accountNo + '\'' +
                ", amount=" + amount +
                ", txNo='" + txNo + '\'' +
                '}';
    }
}
