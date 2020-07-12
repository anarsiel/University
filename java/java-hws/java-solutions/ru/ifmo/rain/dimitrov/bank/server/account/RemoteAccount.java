package ru.ifmo.rain.dimitrov.bank.server.account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteAccount extends BaseAccount {

    public RemoteAccount(String id, int port) throws RemoteException {
        super(id, 0);
        UnicastRemoteObject.exportObject(this, port);
    }
}
