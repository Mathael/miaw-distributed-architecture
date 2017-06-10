package com.chattool.commands;

import com.chattool.model.Account;
import com.chattool.services.AuthenticationRequestService;
import com.chattool.services.impl.AuthenticationRequestServiceImpl;
import com.chattool.util.Message;

/**
 * @author Leboc Philippe.
 */
public class AccountCommand implements ICommandHandler
{
	private static final String[] COMMANDS = {
		"register",
		"login",
		"logout"
	};

    final AuthenticationRequestService authenticationRequestService = new AuthenticationRequestServiceImpl();
	
	@Override
	public boolean useCommand(String command, String... params)
	{
		if(command.equalsIgnoreCase("register"))
		{
            if(params.length != 2) {
                LOGGER.info(Message.COMMAND_WRONG_PARAMETERS);
                LOGGER.info("Utilisation:\nregister username password");
                return true;
            }

            final Account account = authenticationRequestService.register(new Account("-1", params[0], params[1]));
            if(account != null)
                LOGGER.info(String.format("Le compte %s a été créé avec succès", account.getUsername()));
            else
                LOGGER.info(Message.REGISTER_FAIL);
		}
		else if(command.equalsIgnoreCase("login"))
		{
		    if(params.length != 2) {
		        LOGGER.info(Message.COMMAND_WRONG_PARAMETERS);
		        LOGGER.info("Utilisation:\nlogin username password");
		        return true;
            }

            final Account account = authenticationRequestService.login(params[0], params[1]);
            if(account != null) LOGGER.info(Message.LOGIN_REQUEST_SUCCESS); else LOGGER.info(Message.LOGIN_REQUEST_FAIL);
        }
        else if(command.equalsIgnoreCase("logout"))
        {
		    LOGGER.warn("Implement me");
        }
		return true;
	}
	
	@Override
	public String[] getCommandList()
	{
		return COMMANDS;
	}
}
