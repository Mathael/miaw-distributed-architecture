package com.chattool;

import com.chattool.commands.CommandHandler;
import com.chattool.commands.ICommandHandler;
import com.chattool.services.ChannelRequestService;
import com.chattool.services.ChatRequestService;
import com.chattool.services.local.impl.ChannelRequestServiceImpl;
import com.chattool.services.local.impl.ChatRequestServiceImpl;
import com.chattool.util.Message;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Leboc Philippe.
 */
public final class ClientApplication {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

    // Object to read user console entry
    private static final Scanner terminal = new Scanner(System.in);

    // Web Service host
    private static final String WS_RESOURCE_URI = "http://localhost:8080";

    // Client
    private static WebTarget client = null;

    // Services
    public static final ChannelRequestService channelService = new ChannelRequestServiceImpl();
    public static final ChatRequestService chatService = new ChatRequestServiceImpl();

    public static void main(String... args) {

        // Loading Handlers
        CommandHandler.getInstance();

        // Catch "exit" command
        boolean exit = false;

        LOGGER.info("Tapez < help > pour voir la liste des commandes");

        while(!exit)
        {
            // Waiting for client action: handler [params]
            // Exemple: register myusername myuserpassword
            final String userEntry = readClientInput();
            final String[] entries = userEntry.split(" ");

            if(entries[0].equalsIgnoreCase("exit")) exit = true;

            if(!exit) {
                // Retrieve the command handler
                final ICommandHandler handler = CommandHandler.getInstance().getHandler(entries[0]);

                // Execute command
                if (handler != null){
                    String[] params = new String[0];
                    if(entries.length > 1) params = Arrays.copyOfRange(entries, 1, entries.length);
                    handler.useCommand(entries[0], params);
                }
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

    /**
     * @return ask user to write somethings and return the entire line
     */
    public static String readClientInput() {
        return terminal.nextLine();
    }
}
