package com.chattool.commands;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.impl.ApplicationScopeService;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class ChannelCommand implements ICommandHandler {

    private static final String[] COMMANDS = {
        "join",
        "list",
        "quit",
        "create"
    };

    @Override
    public boolean useCommand(String command, String... params) {
        final Account account = ApplicationScopeService.getInstance().getAccount();

        if(account == null) {
            LOGGER.error("Your are not connected");
            return true;
        }

        if(command.equalsIgnoreCase("create"))
        {
            if(params.length != 1) {
                LOGGER.warn("Creation parameter count error"); // TODO
                return true;
            }

            final Channel channel = ClientApplication.channelService.create(new Channel(params[0]));
            if(channel == null) {
                LOGGER.warn("Channel not created !"); // TODO
            }

            // TODO: add client to the newly created channel and notify the client !!!
            LOGGER.info("Channel created successful !");
        }
        else if(command.equalsIgnoreCase("list"))
        {
            LOGGER.info("Liste des cannaux disponible :"); // TODO
            final List<Channel> channels = ClientApplication.channelService.retrieveAllChannels();
            channels.forEach(c -> LOGGER.info("\t"+c.getName()));
        }
        else if(command.equalsIgnoreCase("join"))
        {
            if(params.length != 1) {
                LOGGER.warn("Bad parameters"); // TODO
                return true;
            }

            if(ApplicationScopeService.getInstance().getActiveChannel() != null) {
                LOGGER.info("Vous devez quitter le canal courant."); // TODO
                return true;
            }

            final String channelName = params[0];
            final Channel joinedChannel = ClientApplication.channelService.join(channelName, account);

            if(joinedChannel == null){
                LOGGER.warn("Join failed"); // TODO
                return true;
            }

            final ApplicationScopeService app = ApplicationScopeService.getInstance();
            app.setActiveChannel(joinedChannel);
            LOGGER.info("Vous entrez dans le canal " + joinedChannel.getName());

            final List<Message> messages = ClientApplication.chatService.getMessages(joinedChannel.getId());
            messages.stream()
                    .skip(Math.max(0, messages.size() - 10))
                    .forEach(message -> LOGGER.info(String.format("[%s] %s", message.getAuthor(), message.getContent())));

            if(messages.size() >= 1){
                final Message lastRead = messages.get(messages.size()-1);
                app.startMessageReaderTask(lastRead.getId());
            } else {
                app.startMessageReaderTask(null);
            }
        }
        else if(command.equalsIgnoreCase("quit"))
        {
            final Channel channel = ApplicationScopeService.getInstance().getActiveChannel();
            if(channel == null) {
                LOGGER.info("Vous n'avez encore rejoins aucun canal");
            } else {
                LOGGER.info("Vous avez quitté le canal " + channel.getName());
                final ApplicationScopeService app = ApplicationScopeService.getInstance();
                if(app.getReadMessageTask() != null) app.getReadMessageTask().interrupt();
                app.setActiveChannel(null);
            }
        }
        return true;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
