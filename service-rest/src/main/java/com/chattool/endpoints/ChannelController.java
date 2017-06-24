package com.chattool.endpoints;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.services.local.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(value = "/channel", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Channel create(@RequestBody Channel channel) {
        Assert.notNull(channel, "object channel is null");
        return channelService.create(channel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Channel> showChannelList() {
        return channelService.findAll();
    }

    @RequestMapping(value = "/join/{name}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Channel subscribe(@PathVariable(value = "name") String name, @RequestBody Account account) {
        Assert.notNull(account, "object account is null");
        return channelService.join(name, account);
    }

    @RequestMapping(value = "/quit/{name}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void unsubscribe(@PathVariable(value = "name") String name, @RequestBody Account account) {
        Assert.notNull(account, "object account is null");
        channelService.quit(name, account);
    }
}
