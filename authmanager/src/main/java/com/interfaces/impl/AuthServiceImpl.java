package com.interfaces.impl;

import com.interfaces.AuthService;
import com.model.Account;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Data
public class AuthServiceImpl implements AuthService {

    private static final String ACCOUNTS_FILE_PATH = "accounts.txt";

    // Store logged account
    private List<Account> accounts;

    public AuthServiceImpl(){
        accounts = new ArrayList<>();
    }

    @Override
    public Account connect(String username, String password) {
        System.out.println("Ca marche");
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public Account register(String username, String password) {
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

                if(username.equalsIgnoreCase(items[0]) && password.equalsIgnoreCase(items[1])) {
                    account = new Account(username, password);
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

    @Override
    public void save(Object discussion) {

    }
}
