package com.chattool.endpoints;

import com.chattool.model.Account;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(value = "/social", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SocialController {

    @RequestMapping(value = "/friend/{id}/request", method = RequestMethod.GET)
    public boolean sendFriendRequest(Account account) {
        return false;
    }

    @RequestMapping(value = "/friend/{id}/accept", method = RequestMethod.GET)
    public boolean acceptFriendRequest(Account account) {
        return false;
    }

    @RequestMapping(value = "/friend/{id}/deny", method = RequestMethod.GET)
    public boolean denyFriendRequest(Account account) {
        return false;
    }
}
