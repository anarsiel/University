package ru.ifmo.rain.dimitrov.bank.server.account;

import ru.ifmo.rain.dimitrov.bank.common.Account;

import java.io.Serializable;

public class BaseAccount implements Account, Serializable {
    private final String id;
    private int balance;

    public BaseAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public String getId() {
        return id.substring(id.lastIndexOf(':') + 1);
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

    @Override
    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public synchronized void addBalance(int balanceChange) {
        this.balance += balanceChange;
    }
}
