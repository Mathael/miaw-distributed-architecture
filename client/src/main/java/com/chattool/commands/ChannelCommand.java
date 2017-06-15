package com.chattool.commands;

import com.chattool.ClientApplication;
import com.chattool.model.Channel;

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
        if(command.equals("create"))
        {
            if(params.length != 1) {
                LOGGER.warn("Creation parameter count error"); // TODO
                return true;
            }

            final Channel channel = ClientApplication.channelService.create(new Channel(params[0]));
            if(channel == null) {
                LOGGER.warn("Channel not created !"); // TODO
            }

            ClientApplication.channelService.getActiveChannels().add(channel);
            LOGGER.info("Channel created successful !");
        }
        else if(command.equals("list"))
        {
            LOGGER.info("Liste des cannaux disponible :"); // TODO
            final List<Channel> channels = ClientApplication.channelService.retrieveAllChannels();

            LOGGER.info(""+channels.size());
        }
        else if(command.equals("join"))
        {

        }
        else if(command.equals("quit"))
        {

        }
        return true;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
