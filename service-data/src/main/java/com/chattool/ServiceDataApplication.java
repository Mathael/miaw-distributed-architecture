package com.chattool;

import com.chattool.services.remote.AuthService;
import com.chattool.services.remote.FriendService;
import com.chattool.services.remote.impl.AuthServiceImpl;
import com.chattool.services.remote.impl.FriendServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Leboc Philippe.
 */
public final class ServiceDataApplication {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDataApplication.class);

    // TODO: unhardcode registry port
    private static final int REGISTRY_PORT = 2000;

    public static void main(String... args) {
        try
        {
            final Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);

            // Export des services
            final AuthService authService = (AuthService) UnicastRemoteObject.exportObject(new AuthServiceImpl(), 0);
            final FriendService friendService = (FriendService) UnicastRemoteObject.exportObject(new FriendServiceImpl(), 0);

            // Liaison des services à des nom spécifiques afin de les récupérer
            registry.rebind("authService", authService);
            registry.rebind("friendService", friendService);

            LOGGER.info("Server listening on port :" + REGISTRY_PORT);
        }
        catch (RemoteException e)
        {
            LOGGER.error("Catching RMI Exception: ", e);
        }
    }
}
