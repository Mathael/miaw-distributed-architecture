package com.chattool.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas on 31/05/2017.
 */
@Data
@AllArgsConstructor
public final class Channel {

    private String id;
    private String name;
    private List<Message> messages;
    private List<Account> accounts;

    public Channel() {
        this.messages = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

}
