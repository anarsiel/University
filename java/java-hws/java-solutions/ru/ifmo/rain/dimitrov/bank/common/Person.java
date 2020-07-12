package ru.ifmo.rain.dimitrov.bank.common;

import ru.ifmo.rain.dimitrov.bank.common.exceptions.InternalException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Person extends Remote {

    //

    /**
     * @return persons name
     * @throws RemoteException
     */
    String getName() throws RemoteException;

    /**
     * @return persons surname
     * @throws RemoteException
     */
    String getSurname() throws RemoteException;

    /**
     * @return persons passportId
     * @throws RemoteException
     */
    String getPassportId() throws RemoteException;

    /**
     * @return returns account (local or remote), depending on Person type
     * @throws RemoteException
     */
    Account getAccount(String accountId) throws RemoteException;

    /**
     * @return set of accountsIds of current person
     * @throws RemoteException
     */
    Set<String> getAccounts() throws RemoteException;

    /**
     * creates account for current person with personsPassportId:id and money balance 0
     *
     * @throws RemoteException
     * @throws InternalException if this person already has account with such accountId
     */
    void createAccount(String id) throws RemoteException, InternalException;

}
