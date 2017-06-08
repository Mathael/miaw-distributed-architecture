package com.chattool.services.impl;

import com.chattool.services.AuthService;
import com.chattool.model.Account;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Leboc Philippe.
 */
@Data
public class AuthServiceImpl implements AuthService {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    // Data file
    private static final String ACCOUNTS_FILE_PATH = "accounts.txt";

    // Logging messages
    private static final String CONNECTION_FAIL = "Connection request has failed";
    private static final String CONNECTION_SUCCESS = "Connection request was successful";
    private static final String REGISTER_FAIL = "Register request has fail";
    private static final String REGISTER_SUCCESS = "Register request was successful";
    private static final String FILE_EXCEPTION = "File manipulation exception spotted";
    private static final String FILE_EXCEPTION_READ_FILE = "Unable to open the account file";
    private static final String CANNOT_RETRIEVE_ACCOUNT = "Cannot retrieve account";

    // Store logged account
    private List<Account> onlineAccounts;

    public AuthServiceImpl(){
        onlineAccounts = new ArrayList<>();
    }

    @Override
    public Account connect(String username, String password) {
        final Account account = findAccount(username);
        if(account == null || !account.getPassword().equals(password)) {
            LOGGER.warn(CONNECTION_FAIL);
            return null;
        }

        // User has logged in : store in Online Accounts List
        onlineAccounts.add(account);
        LOGGER.info(CONNECTION_SUCCESS);
        return account;
    }

    @Override
    public void logout(Account account) {
        onlineAccounts.remove(account);
    }

    @Override
    public Account register(String username, String password) {
        final Account existingAccount = findAccount(username);
        if(existingAccount != null) {
            LOGGER.warn(REGISTER_FAIL);
            return null;
        }

        Account account = null;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(ACCOUNTS_FILE_PATH, true));
            writer.println(String.format("%s:%s", username, password));
            writer.close();
            account = new Account(UUID.randomUUID().toString(), username, password);
            LOGGER.info(REGISTER_SUCCESS);
        } catch (Exception e) {
            if(writer != null) writer.close();
            LOGGER.error(FILE_EXCEPTION, e);
        }
        return account;
    }

    @Override
    public boolean remove(String username) throws RemoteException {
        final Account account = findAccount(username);
        if(account == null) {
            LOGGER.warn(CANNOT_RETRIEVE_ACCOUNT);
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

                if(username.equalsIgnoreCase(items[0])) {
                    account = new Account(items[0], items[1],items[2]);
                    // getAccounts().add(account); auto connect.
                }
            }

        } catch (IOException e) {
            LOGGER.error(FILE_EXCEPTION_READ_FILE, e);
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
