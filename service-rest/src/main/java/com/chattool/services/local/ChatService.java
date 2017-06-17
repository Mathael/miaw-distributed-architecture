package com.chattool.services.local;

import com.chattool.model.Account;
import com.chattool.model.Message;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface ChatService {

    boolean say(Account account, Message message);

    List<Message> getMessages(String channelId);

    List<Message> getMessages(String channelId, String lastReadMessageId);
}
