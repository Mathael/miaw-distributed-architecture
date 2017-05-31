package com.interfaces.impl;

import com.interfaces.AuthService;
import com.model.Account;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Data
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private static final String ACCOUNTS_FILE_PATH = "accounts.txt";

    // Logging messages
    private static final String CONNECTION_FAIL = "Connection request has failed";
    private static final String CONNECTION_SUCCESS = "Connection request was successful";
    private static final String REGISTER_FAIL = "Register request has fail";
    private static final String REGISTER_SUCCESS = "Register request was successful";
    private static final String FILE_EXCEPTION = "File manipulation exception spotted";
    private static final String FILE_EXCEPTION_READ_FILE = "Unable to open ";

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
            writer = new PrintWriter(ACCOUNTS_FILE_PATH, "UTF-8");
            writer.println(username+":"+password);
            writer.close();
            account = new Account(username, password);
            LOGGER.info(REGISTER_SUCCESS);
        } catch (Exception e) {
            if(writer != null) writer.close();
            LOGGER.error(FILE_EXCEPTION, e);
        }
        return account;
    }

    @Override
    public void save(Object discussion) {

    }

    /**
     * @param username The account username
     * @return the found account with username equals IGNORE CASE the username parameter | null otherwise
     */
    private Account findAccount(String username) {
        BufferedReader buffer = null;
        FileReader reader = null;
        Account account = null;

        try {
            reader = new FileReader(ACCOUNTS_FILE_PATH);
            buffer = new BufferedReader(reader);
            String line;

            while((account == null) && (line = buffer.readLine()) != null) {
                System.out.println(line);
                // Les utilisateurs sont stocké sous le format:
                // username:password
                String[] items = line.split(":");

                if(username.equalsIgnoreCase(items[0])) {
                    account = new Account(items[0], items[1]);
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
