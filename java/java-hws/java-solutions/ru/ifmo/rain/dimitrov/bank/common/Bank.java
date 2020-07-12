package ru.ifmo.rain.dimitrov.bank.common;

import ru.ifmo.rain.dimitrov.bank.common.exceptions.InternalException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {

    /**
     * creates new person in bank by personal data
     *
     * @param name
     * @param surname
     * @param passport
     * @throws RemoteException
     * @throws InternalException if person with such passportId already exists
     */
    void createPerson(String name, String surname, String passport) throws RemoteException, InternalException;

    /**
     * @param passport
     * @return person as local
     * @throws RemoteException
     */
    Person getLocalPerson(String passport) throws RemoteException;

    /**
     * @param passport
     * @return person as remote link
     * @throws RemoteException
     */
    Person getRemotePerson(String passport) throws RemoteException;

    /**
     * @param name
     * @param surname
     * @param passport
     * @return True if there is no another person with such passportId
     * @throws RemoteException
     */
    boolean isPersonInfoValid(String name, String surname, String passport) throws RemoteException;
}
