package com.chattool.commands;

/**
 * @author Leboc Philippe.
 */
public class ChatCommand implements ICommandHandler {

    private static final String[] COMMANDS = {
        "say",
        "history"
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
