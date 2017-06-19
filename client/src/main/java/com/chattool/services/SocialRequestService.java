package com.chattool.services;

import com.chattool.model.Account;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface SocialRequestService {
    List<String> getOnlineList();
    List<String> showOnlineFriends(Account account);
}
