package com.chattool.services.impl;

import com.chattool.services.AuthService;
import com.chattool.model.Account;
import com.chattool.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.rmi.RemoteException;
import java.util.UUID;

/**
 * @author Leboc Philippe.
 */
public class AuthServiceImpl implements AuthService {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    // Data file
    private static final String ACCOUNTS_FILE_PATH = "accounts.txt";

    @Override
    public Account register(String username, String password) {

        // Security
        if(username.indexOf(':') != -1 || password.indexOf(':') != -1) {
            LOGGER.warn(Message.REGISTRATION_FAIL_ILLEGAL_CHARACTER);
            return null;
        }

        final Account existingAccount = findAccount(username);
        if(existingAccount != null) {
            LOGGER.warn(Message.REGISTER_FAIL);
            return null;
        }

        Account account = null;
        PrintWriter writer = null;
        try {
            account = new Account(UUID.randomUUID().toString(), username, password);
            writer = new PrintWriter(new FileOutputStream(ACCOUNTS_FILE_PATH, true));
            writer.println(String.format("%s:%s:%s", account.getId(), account.getUsername(), account.getPassword()));
            writer.close();
            LOGGER.info(Message.REGISTER_SUCCESS);
        } catch (Exception e) {
            if(writer != null) writer.close();
            LOGGER.error(Message.FILE_EXCEPTION, e);
        }
        return account;
    }

    @Override
    public boolean remove(String username) throws RemoteException {
        final Account account = findAccount(username);
        if(account == null) {
            LOGGER.warn(Message.CANNOT_RETRIEVE_ACCOUNT);
            return false;
        }

        // Remplacer une ligne est bien plus compliqué que réécrire le fichier.
        // Dans le monde réel, on stockerais pas des millions d'utilisateurs de cette façon donc ici on se permet de le faire ;)

        //      - read current file:
        //      - rewrite all account (except the specific account) into a second file
        //      - delete current file and rename the second file to be used as the new file.
        //      - [May be: concurrent access]
        return true;
    }

    /**
     * @param username The account username
     * @return the found account with username equals IGNORE CASE the username parameter | null otherwise
     */
    public Account findAccount(String username) {
        BufferedReader buffer = null;
        FileReader reader = null;
        Account account = null;

        try {
            reader = new FileReader(ACCOUNTS_FILE_PATH);
            buffer = new BufferedReader(reader);
            String line;

            while((account == null) && (line = buffer.readLine()) != null) {
                // Les utilisateurs sont stocké sous le format:
                // username:password
                String[] items = line.split(":");

                if(username.equalsIgnoreCase(items[1])) {
                    account = new Account(items[0], items[1],items[2]);
                }
            }

        } catch (IOException e) {
            LOGGER.error(Message.FILE_EXCEPTION_READ_FILE, e);
        } finally {
            try {
                if(buffer != null) buffer.close();
                if(reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return account;
    }
}
