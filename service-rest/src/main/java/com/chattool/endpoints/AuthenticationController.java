package com.chattool.endpoints;

import com.chattool.ServiceRestApplication;
import com.chattool.model.Account;
import com.chattool.services.local.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(
    value = "/auth",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthenticationController {

    @Autowired
    private AccountingService accountingService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account authentication(@RequestBody Account account) throws Exception {
        Assert.notNull(account, "object account is null");
        return accountingService.login(account.getUsername(), account.getPassword());
    }

    @RequestMapping(value = "/logout/{id}")
    public void logout(@PathVariable(value = "id") String accountId) throws Exception {
        accountingService.logout(accountId);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account account) throws Exception {
        Assert.notNull(account, "account is null or empty");
        return ServiceRestApplication.authService.register(account.getUsername(), account.getPassword());
    }
}
