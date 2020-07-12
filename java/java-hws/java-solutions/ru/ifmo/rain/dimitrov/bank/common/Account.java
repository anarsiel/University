package ru.ifmo.rain.dimitrov.bank.common;

import java.rmi.*;

public interface Account extends Remote {

    /**
     * @return real accountId without "personId:" prefix
     * @throws RemoteException
     */
    String getId() throws RemoteException;

    /**
     * @return current money balance
     * @throws RemoteException
     */
    int getBalance() throws RemoteException;

    /**
     * changes current money balance
     *
     * @param balance
     * @throws RemoteException
     */
    void setBalance(int balance) throws RemoteException;

    /**
     * adds balanceChange to current money balance
     *
     * @param balanceChange
     * @throws RemoteException
     */
    void addBalance(int balanceChange) throws RemoteException;
}