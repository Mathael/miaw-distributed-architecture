package com.chattool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Data
@AllArgsConstructor
@ToString
public final class Account implements Serializable {
    private String id;
    private String username;
    private String password;
    private List<String> friends;

    public Account(){
        this(null, null, null, new ArrayList<>());
    }

    public Account(String id, String username, String password) {
        this(id, username, password, new ArrayList<>());
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Account) && ((Account) o).getId().equals(getId());
    }
}
