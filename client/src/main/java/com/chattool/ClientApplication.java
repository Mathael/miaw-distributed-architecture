package com.chattool;

import com.chattool.commands.CommandHandler;
import com.chattool.commands.ICommandHandler;
import com.chattool.services.ClientService;
import com.chattool.util.Message;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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

    // Client
    private static WebTarget client = null;

    public static void main(String... args) {

        // Loading Handlers
        CommandHandler.getInstance();

        // Catch "quit" command
        boolean exit = false;

        while(!exit)
        {
            displayHelp();

            // Waiting for client action: handler [params]
            // Exemple: register myusername myuserpassword
            final String userEntry = readClientInput();
            final String[] entries = userEntry.split(" ");

            if(entries[0].equalsIgnoreCase("quit")) exit = true;

            if(!exit) {
                // Retrieve the command handler
                final ICommandHandler handler = CommandHandler.getInstance().getHandler(entries[0]);

                // Execute command
                if (handler != null)
                    handler.useCommand(entries[0], entries[1], entries[2]);
                else
                    LOGGER.info(Message.UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * This method prevent from double instance. If client is already instantiated, return it.
     * @return The client used to create requests.
     */
    public static WebTarget webClient() {
        if(client == null)
            client = buildClient();
        return client;
    }

    /**
     * @return WebTarget object corresponding to our Web client used to request the Web Service
     */
    private static WebTarget buildClient() {
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
        LOGGER.info("[" + channelId + "]" + message);
    }

    public static void displayHelp(){
        LOGGER.info("-----------------------------------------------");
        LOGGER.info("- [0] Connexion");
        LOGGER.info("- [1] Créer un compte");
        LOGGER.info("- [2] Déconnexion");
        LOGGER.info("-----------------------------------------------");
        LOGGER.info(">");
    }

    /**
     * @return ask user to write somethings and return the entire line
     */
    public static String readClientInput() {
        return terminal.nextLine();
    }
}
