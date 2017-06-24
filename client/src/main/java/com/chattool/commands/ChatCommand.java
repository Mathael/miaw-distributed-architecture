package com.chattool.commands;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.local.impl.ApplicationScopeService;
import com.chattool.util.SystemMessage;

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
            LOGGER.error(SystemMessage.YOU_ARE_NOT_CONNECTED);
            return true;
        }

        if(command.equalsIgnoreCase("say")) {
            if(params.length < 1) {
                LOGGER.warn(SystemMessage.YOU_MUST_WRITE_A_MESSAGE);
                return true;
            }

            final Channel channel = ApplicationScopeService.getInstance().getActiveChannel();
            if(channel == null) {
                LOGGER.warn(SystemMessage.YOU_MUST_JOIN_A_CHANNEL_TO_WRITE_A_MESSAGE);
                return true;
            }

            // Recover string
            final StringBuilder message = new StringBuilder();
            for (String param : params) {
                message.append(param).append(" ");
            }

            final boolean result = ClientApplication.chatService.say(account, new Message(channel.getId(), message.toString(), account.getUsername()));
            if(!result) LOGGER.warn(SystemMessage.THE_MESSAGE_WASNT_SENT);
        }
        return true;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
