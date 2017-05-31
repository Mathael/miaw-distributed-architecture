package com.interfaces.impl;

import com.interfaces.AuthService;
import com.model.Account;
import lombok.Data;

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

    private static final String ACCOUNTS_FILE_PATH = "accounts.txt";

    // Store logged account
    private List<Account> onlineAccounts;

    public AuthServiceImpl(){
        onlineAccounts = new ArrayList<>();
    }

    @Override
    public Account connect(String username, String password) {
        final Account account = findAccount(username);
        if(account == null || !account.getPassword().equals(password)) return null;
        // User has logged in : store in Online Accounts List
        onlineAccounts.add(account);
        return account;
    }

    @Override
    public void logout(Account account) {
        onlineAccounts.remove(account);
    }

    @Override
    public Account register(String username, String password) {
        final Account existingAccount = findAccount(username);
        if(existingAccount != null) return null;

        Account account = null;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(ACCOUNTS_FILE_PATH, "UTF-8");
            writer.println(username+":"+password);
            writer.close();

            account = new Account(username, password);
        } catch (Exception e) {
            if(writer != null) writer.close();
        }
        return account;
    }

    @Override
    public void save(Object discussion) {

    }

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
                    account = new Account(username, items[1]);
                    // getAccounts().add(account); auto connect.
                    System.out.println("Utilisateur récupéré");
                }
            }

        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier listant les comptes.");
            e.printStackTrace();
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
