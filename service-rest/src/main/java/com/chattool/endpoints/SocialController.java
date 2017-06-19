package com.chattool.endpoints;

import com.chattool.model.Account;
import com.chattool.services.local.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(value = "/social", produces = MediaType.APPLICATION_JSON_VALUE)
public class SocialController {

    @Autowired
    private AccountingService accountingService;

    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public List<String> showOnlineList() {
        return accountingService.getOnlineList().stream().map(Account::getUsername).collect(Collectors.toList());
    }

    @RequestMapping(value = "/onlinefriend", method = RequestMethod.GET)
    public List<String> showOnlineList(@RequestParam(value = "accountId") String accountId) {
        final Account account = accountingService.find(accountId);
        return accountingService
                .getOnlineList()
                .stream()
                .map(Account::getUsername)
                .filter(s -> account.getFriends().contains(s))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/friend/{id}/request", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendFriendRequest(@RequestBody Account account) {
        return false;
    }

    @RequestMapping(value = "/friend/{id}/accept", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean acceptFriendRequest(@RequestBody Account account) {
        return false;
    }

    @RequestMapping(value = "/friend/{id}/deny", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean denyFriendRequest(@RequestBody Account account) {
        return false;
    }
}
