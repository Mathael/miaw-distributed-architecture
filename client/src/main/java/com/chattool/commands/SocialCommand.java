package com.chattool.commands;

/**
 * @author Leboc Philippe.
 */
public class SocialCommand implements ICommandHandler {

    private static final String[] COMMANDS = {
        "online-list",
        "online-friends",
        "request-friend",
        "request-friend-accept",
        "request-friend-deny",
        "remove-friend"
    };

    @Override
    public boolean useCommand(String command, String... params) {


        if(command.equalsIgnoreCase("online-list"))
        {
            // TODO:
        }
        else if(command.equalsIgnoreCase("online-friends"))
        {
            // TODO:
        }
        else if(command.equalsIgnoreCase("request-friend"))
        {
            // TODO:
        }
        else if(command.equalsIgnoreCase("request-friend-accept"))
        {
            // TODO:
        }
        else if(command.equalsIgnoreCase("request-friend-deny"))
        {
            // TODO:
        }
        else if(command.equalsIgnoreCase("remove-friend"))
        {
            // TODO:
        }

        return true;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
