package com.chattool.services;

/**
 * @author Leboc Philippe.
 */
public interface ChatService {
    // Channels
    boolean joinChannel(String channelId);
    void exitChannel(String channelId);

    // chat
    void say(String channelId, String message);
}
