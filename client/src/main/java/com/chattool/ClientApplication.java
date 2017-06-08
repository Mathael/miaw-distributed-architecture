package com.chattool;

import com.chattool.model.Account;
import com.chattool.services.ClientService;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.Scanner;

/**
 * @author Leboc Philippe.
 */
public final class ClientApplication implements ClientService {

    private static final Scanner console = new Scanner(System.in);
    private static final int OPTION_LOGIN = 0;
    private static final int OPTION_REGISTER = 1;
    private static final int OPTION_DISCONNECT = 2;

    public static void main(String... args) {

        int choice = -1;

        while(choice != OPTION_DISCONNECT) {

            displayHelp();
            final String userEntry = askUser();
            final String[] entries = userEntry.split(":");

            choice = tryParseInt(entries[0]);

            switch (choice)
            {
                case OPTION_LOGIN: break;
                case OPTION_REGISTER:

                    final Account createdAccount =
                        buildClient("http://localhost:8080")
                        .path("/chatAuthService/register")
                        .request()
                        .accept(MediaType.APPLICATION_XML)
                        .post(Entity.xml(new Account("-1", "Alpha", "Test")))
                        .readEntity(Account.class);

                    if(createdAccount != null) System.out.println(createdAccount.toString());
                    break;
                case OPTION_DISCONNECT: break;
                default: System.out.println("Ce choix n'existe pas !");
            }
        }
    }

    public static WebTarget buildClient(String resourceUri) {
        final ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.READ_TIMEOUT, 30000)
                .property(ClientProperties.CONNECT_TIMEOUT, 5000);

        return ClientBuilder
                .newClient(clientConfig)
                .register(new LoggingFeature())
                .target(resourceUri);
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

    public static String askUser() {
        return console.nextLine();
    }

    private static int tryParseInt(String value) {
        int number;
        try { number = Integer.parseInt(value); } catch(NumberFormatException nfe) { number = -2; }
        return number;
    }
}
