package com;

import com.interfaces.authmanager.AuthService;
import com.interfaces.impl.AuthServiceImpl;

import java.rmi.AlreadyBoundException;
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

            final AuthServiceImpl auth = new AuthServiceImpl();
            final AuthService authService = (AuthService) UnicastRemoteObject.exportObject(auth, 0);
            registry.bind("authService", authService);

            System.out.println("Server started...");
        }
        catch (RemoteException | AlreadyBoundException e)
        {
            e.printStackTrace();
        }
    }
}
