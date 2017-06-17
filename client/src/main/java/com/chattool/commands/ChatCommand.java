package com.chattool.commands;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.local.impl.ApplicationScopeService;

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
        final Account account = ApplicationScopeService.getInstance().getAccount();

        if(account == null) {
            LOGGER.error("Your are not connected"); // TODO
            return true;
        }

        if(command.equalsIgnoreCase("say")) {
            if(params.length < 1) {
                LOGGER.warn("Vous devez spécifier un message"); // TODO
                return true;
            }

            final Channel channel = ApplicationScopeService.getInstance().getActiveChannel();
            if(channel == null) {
                LOGGER.warn("Vous devez rejoindre un cannal pour écrire un message"); // TODO
                return true;
            }

            // Recover string
            final StringBuilder message = new StringBuilder();
            for (String param : params) {
                message.append(param).append(" ");
            }

            final boolean result = ClientApplication.chatService.say(account, new Message(channel.getId(), message.toString(), account.getUsername()));
            if(!result) LOGGER.warn("Le message n'a pas pu être envoyé"); // TODO
        }
        return true;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
