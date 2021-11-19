package com.misitontic.account.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Transaction {
    @Id
    private String id;
    private String originAccount;
    private Integer balanceOrigin;
    private String destinyAccount;
    private Integer balanceDestiny;
    private Integer value;
    private Date date;

    public Transaction(String originAccount, String destinyAccount, Integer value) {
        this.originAccount = originAccount;
        this.destinyAccount = destinyAccount;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public Integer getBalanceOrigin() {
        return balanceOrigin;
    }

    public void setBalanceOrigin(Integer balanceOrigin) {
        this.balanceOrigin = balanceOrigin;
    }

    public String getDestinyAccount() {
        return destinyAccount;
    }

    public void setDestinyAccount(String destinyAccount) {
        this.destinyAccount = destinyAccount;
    }

    public Integer getBalanceDestiny() {
        return balanceDestiny;
    }

    public void setBalanceDestiny(Integer balanceDestiny) {
        this.balanceDestiny = balanceDestiny;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
