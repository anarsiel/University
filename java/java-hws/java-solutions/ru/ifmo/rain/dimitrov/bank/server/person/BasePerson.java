package ru.ifmo.rain.dimitrov.bank.server.person;

import ru.ifmo.rain.dimitrov.bank.common.Account;
import ru.ifmo.rain.dimitrov.bank.common.exceptions.InternalException;
import ru.ifmo.rain.dimitrov.bank.common.Person;
import ru.ifmo.rain.dimitrov.bank.server.account.LocalAccount;
import ru.ifmo.rain.dimitrov.bank.server.account.RemoteAccount;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BasePerson implements Person, Serializable {
    private final String name;
    private final String surname;
    private final String passportId;

    private final ConcurrentHashMap<String, Account> accounts;

    public BasePerson(String name, String surname, String passportId, ConcurrentHashMap<String, Account> accounts) {
        this.name = name;
        this.surname = surname;
        this.passportId = passportId;
        this.accounts = accounts;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPassportId() {
        return passportId;
    }

    @Override
    public Set<String> getAccounts() {
        return accounts.keySet();
    }

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public synchronized void createAccount(String accountId) throws RemoteException {
        if (accounts.get(accountId) != null) {
            throw new InternalException("Account with such passportId already exists: " + accountId);
        }

        if (this instanceof RemotePerson) {
            accounts.put(accountId, new RemoteAccount(passportId + ":" + accountId, 0));
        } else {
            accounts.put(accountId, new LocalAccount(passportId + ":" + accountId, 0));
        }
    }
}
