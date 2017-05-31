package com;

import com.interfaces.authmanager.AuthService;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

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

            System.out.println("Récupération du registre " + REGISTRY_HOST + ":" + REGISTRY_PORT);
            final Registry registry = LocateRegistry.getRegistry(REGISTRY_HOST, REGISTRY_PORT);
            final AuthService authService = (AuthService) registry.lookup("authService");

            authService.connect();

            //final Abonne client = new AbonneImpl();
            //final Abonne abonne = (Abonne) UnicastRemoteObject.exportObject(client, 0);

            //if(infoTraficService.abonner(abonne)) {
            //    registry.bind(UUID.randomUUID().toString(), abonne);
            //}
        }
        catch (RemoteException | NotBoundException | ClassCastException e)
        {
            e.printStackTrace();
        }
    }
}
