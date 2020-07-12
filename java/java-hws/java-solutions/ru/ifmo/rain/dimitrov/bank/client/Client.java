package ru.ifmo.rain.dimitrov.bank.client;

import ru.ifmo.rain.dimitrov.bank.common.Account;
import ru.ifmo.rain.dimitrov.bank.common.Bank;
import ru.ifmo.rain.dimitrov.bank.common.Person;
import ru.ifmo.rain.dimitrov.bank.common.exceptions.InternalException;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private static void validateArgs(String[] args) {
        if (args.length != 5) {
            throw new InternalException(
                    "There must be 5 arguments instead of " + args.length +
                            ": [name], [surname], [passportId], [accountId], [sumChange]."
            );
        }

        try {
            Integer.parseInt(args[4]);
        } catch (Exception e) {
            throw new InternalException("Argument #5, sum change - must be int.");
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        try {
            final Bank bank;

            try {
                Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
                bank = (Bank) registry.lookup("//localhost/bank");
            } catch (final NotBoundException e) {
                throw new InternalException("Registry a name has no associated binding.", e);
            } catch (final ConnectException e) {
                throw new InternalException("Connection error. Check if server is started", e);
            }

            try {
                validateArgs(args);
            } catch (InternalException e) {
                throw new InternalException("Arguments validation error.", e);
            }

            final String name = args[0];
            final String surname = args[1];
            final String passportId = args[2];
            final String accountId = args[3];
            final int sumChange = Integer.parseInt(args[4]);

            if (!bank.isPersonInfoValid(name, surname, passportId)) {
                throw new InternalException(String.format("Passport id: %s doesn't belong to %s %s", passportId, name, surname));
            }

            Person person;
            Account account;

            person = bank.getRemotePerson(passportId);
            if (person == null) {
                try {
                    bank.createPerson(name, surname, passportId);
                    log("Added new person with passport: " + passportId);
                } catch (InternalException ignored) {
                    // ignored
                } finally {
                    person = bank.getRemotePerson(passportId);
                }
            }

            account = person.getAccount(accountId);
            if (account == null) {
                try {
                    person.createAccount(accountId);
                    log("Added new account with passport: " + passportId + " to person with passport: " + person.getPassportId());
                } catch (InternalException ignored) {
                    // ignored
                } finally {
                    account = person.getAccount(accountId);
                }
            }

            log("Account id: " + account.getId());
            log("Initial balance : " + account.getBalance());
            account.addBalance(sumChange);
            log("Final balance: " + account.getBalance());
        } catch (RemoteException e) {
            System.err.println("Remote exception thrown while executing" + e.getMessage());
        } catch (InternalException e) {
            System.err.println(e.getMessage());
        }
    }
}
