package com.chattool;

import com.chattool.services.remote.AuthService;
import com.chattool.services.remote.FriendService;
import com.chattool.util.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@SpringBootApplication
@EnableAutoConfiguration
public class ServiceRestApplication {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRestApplication.class);

    // Registry params
    private static String REGISTRY_HOST = "127.0.0.1";
    private static int REGISTRY_PORT = 2000;

    public static AuthService authService = null;
    public static FriendService friendService = null;

	public static void main(String[] args) {
        try
        {
            // We can set 1 or 2 params to change default registry host and port
            if(args.length == 1) REGISTRY_HOST = args[0];
            if(args.length == 2) {
                REGISTRY_HOST = args[0];
                REGISTRY_PORT = Integer.parseInt(args[1]);
            }

            // Registry connection
            LOGGER.info(String.format("%s %s : %s", SystemMessage.REGISTRY_LOCATE, REGISTRY_HOST, REGISTRY_PORT));
            final Registry registry = LocateRegistry.getRegistry(REGISTRY_HOST, REGISTRY_PORT);

            if(registry == null) {
                LOGGER.error(SystemMessage.REGISTRY_CANNOT_BE_LOCATED);
                throw new RemoteException();
            }

            // Récupération des services qui sont sur le registry
            authService = (AuthService) registry.lookup("authService");
            Assert.notNull(authService,"authService is null...");

            friendService = (FriendService) registry.lookup("friendService");
            if(friendService == null) {
                LOGGER.warn("friendService is null...");
            }

            // TODO: retrieve messages history
            //final MessageService messageService = (MessageService) registry.lookup("messageService");

            // Start API REST
            SpringApplication.run(ServiceRestApplication.class, args);
        }
        catch (RemoteException | NotBoundException | ClassCastException e)
        {
            LOGGER.error("Catching RMI Exception: ", e);
        }
    }
}
