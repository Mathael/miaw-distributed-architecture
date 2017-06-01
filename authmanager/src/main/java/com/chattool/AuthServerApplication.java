package com.chattool;

import com.chattool.services.AuthService;
import com.chattool.services.MessageService;
import com.chattool.services.impl.AuthServiceImpl;
import com.chattool.services.impl.MessageServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Leboc Philippe.
 */
public final class AuthServerApplication {

    private static final int REGISTRY_PORT = 2000;

    public static void main(String... args) {
        try
        {
            final Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);

            // Export des services
            final AuthService authService = (AuthService) UnicastRemoteObject.exportObject(new AuthServiceImpl(), 0);
            final MessageService messageService = (MessageService) UnicastRemoteObject.exportObject(new MessageServiceImpl(), 0);

            // Liaison des services à des nom spécifiques afin de les récupérer
            registry.rebind("authService", authService);
            registry.rebind("messageService", messageService);

            System.out.println("Server listening on 127.0.0.1:" + REGISTRY_PORT);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
