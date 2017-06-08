package com.chattool;

import com.chattool.model.Account;
import com.chattool.services.ClientService;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Scanner;

/**
 * @author Leboc Philippe.
 */
public final class ClientApplication implements ClientService {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

    // Object to read user console entry
    private static final Scanner terminal = new Scanner(System.in);

    // Web Service host
    private static final String WS_RESOURCE_URI = "http://localhost:8080";

    // Client inputs choice
    private static final int OPTION_LOGIN = 1;
    private static final int OPTION_REGISTER = 2;
    private static final int OPTION_DISCONNECT = 3;

    public static void main(String... args) {

        int choice = 0;

        while(choice != OPTION_DISCONNECT) {

            displayHelp();
            final String userEntry = readClientInput();
            final String[] entries = userEntry.split(":");

            choice = tryParseInt(entries[0]);

            switch (choice)
            {
                case OPTION_LOGIN: break;
                case OPTION_REGISTER:
                {
                    final Account account =
                        buildClient()
                            .path("/chatAuthService/register")
                            .request()
                            .accept(MediaType.APPLICATION_XML)
                            .post(Entity.xml(new Account("-1", entries[1], entries[2])))
                            .readEntity(Account.class);

                    if(account != null) {
                        LOGGER.info(String.format("Le compte %s a été créé avec succès", account.getUsername()));
                    }
                    break;
                }
                case OPTION_DISCONNECT:
                    LOGGER.info("Vous venez de vous déconnecter.");
                    break;
                default: System.out.println("Ce choix n'existe pas !");
            }
        }

    }

    public static WebTarget buildClient() {
        final ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.READ_TIMEOUT, 8000)
                .property(ClientProperties.CONNECT_TIMEOUT, 5000);

        return ClientBuilder
                .newClient(clientConfig)
                .register(new LoggingFeature())
                .target(WS_RESOURCE_URI);
    }

    @Override
    public void displayMessage(String channelId, String message) {
        System.out.println("[" + channelId + "]" + message);
    }

    public static void displayHelp(){
        System.out.println("-----------------------------------------------");
        System.out.println("- [0] Connexion");
        System.out.println("- [1] Créer un compte");
        System.out.println("- [2] Déconnexion");
        System.out.println("-----------------------------------------------");
        System.out.println(">");
    }

    public static String readClientInput() {
        return terminal.nextLine();
    }

    private static int tryParseInt(String value) {
        int number;
        try { number = Integer.parseInt(value); } catch(NumberFormatException nfe) { number = 0; }
        return number;
    }
}
