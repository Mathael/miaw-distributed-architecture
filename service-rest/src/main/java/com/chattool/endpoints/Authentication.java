package com.chattool.endpoints;

import com.chattool.ServiceRestApplication;
import com.chattool.model.Account;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(
    value = "/chatAuthService",
    consumes = {MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_XML_VALUE})
public class Authentication {

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public Account authentication(@RequestParam(value="username") String username, @RequestParam(value="password") String password) throws Exception {
        Assert.hasLength(username, "parameter username is null or empty");
        Assert.hasLength(password, "parameter password is null or empty");
        //return ServiceRestApplication.authService.connect(username, password);
        return null;
    }

    @RequestMapping(value = "/logout")
    public void logout(Account account) throws Exception {}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account account) throws Exception {
        Assert.notNull(account, "account is null or empty");
        return ServiceRestApplication.authService.register(account.getUsername(), account.getPassword());
    }
}
