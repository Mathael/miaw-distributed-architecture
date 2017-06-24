package com.chattool.services.remote.impl;

import com.chattool.model.Account;
import com.chattool.services.remote.FriendService;
import com.chattool.util.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Leboc Philippe.
 */
public class FriendServiceImpl implements FriendService {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(FriendServiceImpl.class);

    // Data file
    private static final String FRIENDS_FILE_PATH = "friends.txt";

    public FriendServiceImpl() {
        final File file = new File(FRIENDS_FILE_PATH);
        try
        {
            if(!file.exists() && file.createNewFile()) LOGGER.info("New file has been created to store friends data.");
        } catch (IOException e) {
            LOGGER.error("Catch error when trying to create file", e);
        }
    }

    @Override
    public Account saveNewFriend(Account account, Account friendAccount) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(FRIENDS_FILE_PATH, true));
            writer.println(String.format("%s:%s", account.getId(), friendAccount.getId()));
            writer.close();

            // Store friend username in Account class
            account.getFriends().add(friendAccount.getUsername());
            // LOGGER.info(SystemMessage.FRIEND_ADDED_SUCCESSFUL);
        } catch (Exception e) {
            if (writer != null) writer.close();
            LOGGER.error(SystemMessage.FILE_EXCEPTION, e);
        }

        return account;
    }

    @Override
    public Account getFriends(Account account) {
        BufferedReader buffer = null;
        FileReader reader = null;

        try {
            reader = new FileReader(FRIENDS_FILE_PATH);
            buffer = new BufferedReader(reader);
            String line;

            while ((line = buffer.readLine()) != null) {
                // Les utilisateurs sont stocké sous le format:
                // username:password
                String[] items = line.split(":");

                if (account.getId().equals(items[0])) {
                    // We must check if the friend account exist but we will bypass this security
                    account.getFriends().add(items[1]);
                }
            }

        } catch (IOException e) {
            LOGGER.error(SystemMessage.FILE_EXCEPTION_READ_FILE, e);
        } finally {
            try {
                if (buffer != null) buffer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return account;
    }
}
