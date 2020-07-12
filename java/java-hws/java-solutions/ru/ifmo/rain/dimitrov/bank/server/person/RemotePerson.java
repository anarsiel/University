package ru.ifmo.rain.dimitrov.bank.server.person;

import ru.ifmo.rain.dimitrov.bank.common.Account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

public class RemotePerson extends BasePerson {

    public RemotePerson(String name, String surname, String passportId,
                        ConcurrentHashMap<String, Account> remoteAccounts, int port) throws RemoteException {
        super(name, surname, passportId, remoteAccounts);
        UnicastRemoteObject.exportObject(this, port);
    }
}
