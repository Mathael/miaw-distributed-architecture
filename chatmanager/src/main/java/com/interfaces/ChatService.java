package com.interfaces;

/**
 * @author Leboc Philippe.
 */
public interface ChatService {
    // Authentication : appel des interfaces
    Object connect();
    void logout();
    Object register();

    // Channels
    boolean joinChannel(String channelId);
    void exitChannel(String channelId);

    // chat
    void say(String channelId, String message);

    // Historique
    // TODO

    // Gestion des comptes
    // TODO
}
