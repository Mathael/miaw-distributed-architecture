package com.chattool;

import com.chattool.services.AuthService;
import com.chattool.model.Account;
import com.chattool.services.MessageService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * @author Leboc Philippe.
 */
public final class ChatServerApplication {

    private static String REGISTRY_HOST = "127.0.0.1";
    private static int REGISTRY_PORT = 2000;

    public static void main(String... args) {
        try
        {
            // Permet de redéfinir l'IP
            if(args.length == 1) REGISTRY_HOST = args[0];

            // Permet de redéfinir l'IP et le PORT
            if(args.length == 2) {
                REGISTRY_HOST = args[0];
                REGISTRY_PORT = Integer.parseInt(args[1]);
            }

            // Connexion au registry
            System.out.println("Récupération du registre " + REGISTRY_HOST + ":" + REGISTRY_PORT);
            final Registry registry = LocateRegistry.getRegistry(REGISTRY_HOST, REGISTRY_PORT);

            // Récupération des services qui sont sur le registry
            final AuthService authService = (AuthService) registry.lookup("authService");
            final MessageService messageService = (MessageService) registry.lookup("messageService");

            final Scanner sc = new Scanner(System.in);
            System.out.println("-----------------------------------------------");
            System.out.println("-\t[1] Afficher cette aide");
            System.out.println("-\t[2] Création d'un compte");
            System.out.println("-\t[3] Suppression d'un compte");
            System.out.println("-----------------------------------------------");
            System.out.print(">");

            while(true) {
                final String[] entry = sc.nextLine().split(":");
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
            e.printStackTrace();
        }
    }
}
