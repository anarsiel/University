package ru.ifmo.rain.dimitrov.bank.server.bank;

import ru.ifmo.rain.dimitrov.bank.common.Account;
import ru.ifmo.rain.dimitrov.bank.common.Bank;
import ru.ifmo.rain.dimitrov.bank.common.exceptions.InternalException;
import ru.ifmo.rain.dimitrov.bank.common.Person;
import ru.ifmo.rain.dimitrov.bank.server.account.LocalAccount;
import ru.ifmo.rain.dimitrov.bank.server.person.LocalPerson;
import ru.ifmo.rain.dimitrov.bank.server.person.RemotePerson;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteBank extends UnicastRemoteObject implements Bank {
    private final int port;

    private final ConcurrentHashMap<String, Person> personById = new ConcurrentHashMap<>();

    public RemoteBank(int port) throws RemoteException {
        super(port);
        this.port = port;
    }

    @Override
    public Person getLocalPerson(String passport) throws RemoteException {
        Person person = personById.get(passport);
        Set<String> remotePersonAccounts = person.getAccounts();

        ConcurrentHashMap<String, Account> localPersonAccounts = new ConcurrentHashMap<>();
        remotePersonAccounts.forEach((x) -> {
            try {
                Account curRemote = person.getAccount(x);
                localPersonAccounts.put(x, new LocalAccount(curRemote.getId(), curRemote.getBalance()));
            } catch (RemoteException e) {
                System.out.println("Error occurred while creating local person. " + e);
            }
        });

        return new LocalPerson(person.getName(), person.getSurname(), person.getPassportId(), localPersonAccounts);
    }

    @Override
    public Person getRemotePerson(String passportId) {
        return personById.get(passportId);
    }

    @Override
    public synchronized void createPerson(String name, String surname, String passport) throws RemoteException, InternalException {
        if (personById.containsKey(passport)) {
            throw new InternalException("Person with such passportId already exists: " + passport);
        }

        if (isPersonInfoValid(name, surname, passport)) {
            personById.put(passport, new RemotePerson(name, surname, passport, new ConcurrentHashMap<>(), port));
        }
    }

    @Override
    public boolean isPersonInfoValid(String name, String surname, String passport) throws RemoteException {
        if (name == null || surname == null || passport == null) {
            return false;
        }

        Person person = personById.get(passport);

        return person == null ||                    // if person is new
               person.getName().equals(name) &&
               person.getSurname().equals(surname);
    }
}
