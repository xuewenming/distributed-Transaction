package com.sam.tcc.bank1.pojo;

import java.io.Serializable;

/**
 * @author Mr.xuewenming
 * @title: AccountInfo
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1916:36
 */
public class AccountInfo implements Serializable {
    private Long id;
    private String accountName;
    private String accountNo;
    private String accountPassword;
    private Double accountBalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
