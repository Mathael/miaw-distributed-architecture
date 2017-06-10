package com.chattool;

import com.chattool.services.AuthService;
import com.chattool.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

@SpringBootApplication
public class ServiceRestApplication {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRestApplication.class);

    // Registry params
    private static String REGISTRY_HOST = "127.0.0.1";
    private static int REGISTRY_PORT = 2000;

    // Terminal inputs
    private static final Scanner terminal = new Scanner(System.in);

    public static AuthService authService = null;

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
            LOGGER.info(String.format("%s %s : %s", Message.REGISTRY_LOCATE, REGISTRY_HOST, REGISTRY_PORT));
            final Registry registry = LocateRegistry.getRegistry(REGISTRY_HOST, REGISTRY_PORT);

            if(registry == null) {
                LOGGER.error(Message.REGISTRY_CANNOT_BE_LOCATED);
                throw new RemoteException();
            }

            // Récupération des services qui sont sur le registry
            authService = (AuthService) registry.lookup("authService");
            //final MessageService messageService = (MessageService) registry.lookup("messageService");

            // Start API REST
            SpringApplication.run(ServiceRestApplication.class, args);

            // Start terminal mode to handle administration
            // TODO: change me

            LOGGER.info("-----------------------------------------------");
            LOGGER.info("-\t[1] Afficher cette aide");
            LOGGER.info("-\t[2] Création d'un compte");
            LOGGER.info("-\t[3] Suppression d'un compte");
            LOGGER.info("-----------------------------------------------");
            System.out.print(">");

            while(true) {
                final String[] entry = readClientInput().split(":");
                final String choice = entry[0];
                switch (choice) {
                    case "1":
                        break;
                    case "2":
                        authService.register(entry[1], entry[2]);
                        break;
                    case "3":
                        authService.remove(entry[1]);
                        break;
                }
            }
        }
        catch (RemoteException | NotBoundException | ClassCastException e)
        {
            LOGGER.error("Catching RMI Exception: ", e);
        }
    }

    /**
     * @return ask user to write somethings and return the entire line
     */
    public static String readClientInput() {
        return terminal.nextLine();
    }
}
