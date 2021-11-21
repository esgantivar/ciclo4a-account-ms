package com.misitontic.account.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
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
}
