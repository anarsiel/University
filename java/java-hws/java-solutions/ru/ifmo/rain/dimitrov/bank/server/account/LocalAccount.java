package ru.ifmo.rain.dimitrov.bank.server.account;

import java.io.Serializable;

public class LocalAccount extends BaseAccount implements Serializable {

    public LocalAccount(String id, int balance) {
        super(id, balance);
    }
}
