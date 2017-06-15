package com.chattool.services;

import com.chattool.model.Account;
import com.chattool.model.Message;

/**
 * @author Leboc Philippe.
 */
public interface ChatRequestService {
    boolean say(Account account, Message message);
}
