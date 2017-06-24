package com.chattool.commands;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.services.local.impl.ApplicationScopeService;
import com.chattool.util.SystemMessage;

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

        final Account account = ApplicationScopeService.getInstance().getAccount();

        if(account == null) {
            LOGGER.info(SystemMessage.YOU_ARE_NOT_CONNECTED);
            return true;
        }

        if(command.equalsIgnoreCase("online-list"))
        {
            LOGGER.info("================== Online ==================");
            ClientApplication
                .socialService
                .getOnlineList()
                .forEach(LOGGER::info);
        }
        else if(command.equalsIgnoreCase("online-friends"))
        {
            LOGGER.info("============== Online Friends ==============");
            ClientApplication
                    .socialService
                    .showOnlineFriends(account)
                    .forEach(LOGGER::info);
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
