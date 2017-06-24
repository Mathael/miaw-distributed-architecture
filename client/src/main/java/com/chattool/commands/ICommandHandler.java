package com.chattool.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Leboc Philippe.
 */
public interface ICommandHandler {
    Logger LOGGER = LoggerFactory.getLogger(ICommandHandler.class);

    /**
     * @param command   The command to be registered
     * @param params    All the params added to the command
     * @return
     */
    boolean useCommand(String command, String... params);

    /**
     * @return  All the command list affected to the current Handler
     */
    String[] getCommandList();
}
