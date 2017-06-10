package com.chattool.commands;

/**
 * @author Leboc Philippe.
 */
public class ChannelCommand implements ICommandHandler {

    private static final String[] COMMANDS = {
        "join",
        "list",
        "quit"
    };

    @Override
    public boolean useCommand(String command, String... params) {
        // TODO: implements me
        return false;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
