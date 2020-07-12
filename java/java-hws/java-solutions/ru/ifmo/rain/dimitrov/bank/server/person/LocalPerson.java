package ru.ifmo.rain.dimitrov.bank.server.person;

import ru.ifmo.rain.dimitrov.bank.common.Account;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class LocalPerson extends BasePerson implements Serializable {

    public LocalPerson(String name, String surname, String passportId,
                       ConcurrentHashMap<String, Account> localAccounts) {
        super(name, surname, passportId, localAccounts);
    }
}
