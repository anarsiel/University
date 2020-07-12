package ru.ifmo.rain.dimitrov.bank.server;

import ru.ifmo.rain.dimitrov.bank.common.Bank;
import ru.ifmo.rain.dimitrov.bank.common.exceptions.InternalException;
import ru.ifmo.rain.dimitrov.bank.server.bank.RemoteBank;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    private static void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        try {
            Registry registry;
            try {
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
                registry = LocateRegistry.getRegistry();
            } catch (RemoteException e) {
                throw new InternalException(
                        "Error occurred while creating registry. Port is unavailable: " + Registry.REGISTRY_PORT, e
                );
            }

            try {
                final Bank bank = new RemoteBank(Registry.REGISTRY_PORT);
                registry.rebind("//localhost/bank", bank);
            } catch (RemoteException e) {
                throw new InternalException(
                        "Remote exception thrown while executing. Check your port: " + Registry.REGISTRY_PORT, e
                );
            }
            log("Server started");
        } catch (InternalException e) {
            log(e.getMessage());
        }
    }
}
