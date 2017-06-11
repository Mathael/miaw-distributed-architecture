package com.chattool.endpoints;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(value = "/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Channel> showChannelList() {
        return channelService.findAll();
    }

    @RequestMapping(value = "/{id}/join", method = RequestMethod.PUT)
    public Channel subscribe(@PathVariable(value = "id") String id, @RequestBody Account account) {
        return channelService.join(id, account);
    }

    @RequestMapping(value = "/{id}/quit", method = RequestMethod.PUT)
    public void unsubscribe(@PathVariable(value = "id") String id, @RequestBody Account account) {
        channelService.exit(id, account);
    }
}
