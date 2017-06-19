package com.chattool.services.remote.impl;

import com.chattool.enums.AccountSearchType;
import com.chattool.services.remote.AuthService;
import com.chattool.model.Account;
import com.chattool.util.SystemMessage;
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
            LOGGER.warn(SystemMessage.REGISTRATION_FAIL_ILLEGAL_CHARACTER);
            return null;
        }

        final Account existingAccount = findAccount(username, AccountSearchType.USERNAME);
        if(existingAccount != null) {
            LOGGER.warn(SystemMessage.REGISTER_FAIL);
            return null;
        }

        Account account = null;
        PrintWriter writer = null;
        try {
            account = new Account(UUID.randomUUID().toString(), username, password);
            writer = new PrintWriter(new FileOutputStream(ACCOUNTS_FILE_PATH, true));
            writer.println(String.format("%s:%s:%s", account.getId(), account.getUsername(), account.getPassword()));
            writer.close();
            LOGGER.info(SystemMessage.REGISTER_SUCCESS);
        } catch (Exception e) {
            if(writer != null) writer.close();
            LOGGER.error(SystemMessage.FILE_EXCEPTION, e);
        }
        return account;
    }

    @Override
    public boolean remove(String username) throws RemoteException {
        final Account account = findAccount(username, AccountSearchType.USERNAME);
        if(account == null) {
            LOGGER.warn(SystemMessage.CANNOT_RETRIEVE_ACCOUNT);
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
     * @param key The account param to find by [username or identifier]
     * @param type The search type
     * @return the found account with username equals IGNORE CASE the username parameter | null otherwise
     */
    @Override
    public Account findAccount(String key, AccountSearchType type) {
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

                if(type == AccountSearchType.ID)
                    account = findById(key, items);
                else
                    account = findByUsername(key, items);
            }

        } catch (IOException e) {
            LOGGER.error(SystemMessage.FILE_EXCEPTION_READ_FILE, e);
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

    private Account findByUsername(String username, String[] data) {
        if(username.equalsIgnoreCase(data[1]))
            return new Account(data[0], data[1], data[2]);
        return null;
    }

    private Account findById(String id, String[] data) {
        if(id.equals(data[0]))
            return new Account(data[0], data[1], data[2]);
        return null;
    }
}
