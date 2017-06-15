package com.chattool.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Leboc Philippe.
 */
public interface ICommandHandler {
    Logger LOGGER = LoggerFactory.getLogger(ICommandHandler.class);

    /**
     * this is the worker method that is called when someone uses an admin command.
     * @param command
     * @param params
     * @return command success
     */
    boolean useCommand(String command, String... params);

    /**
     * this method is called at initialization to register all the item ids automatically
     * @return all known itemIds
     */
    String[] getCommandList();


    /**
     * @param value The numeric value as string
     * @return The numeric value as Integer type
     */
    default int tryParseInt(String value) {
        int number;
        try { number = Integer.parseInt(value); } catch(NumberFormatException nfe) { number = 0; }
        return number;
    }
}
