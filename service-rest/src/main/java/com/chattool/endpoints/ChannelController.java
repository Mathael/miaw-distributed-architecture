package com.chattool.endpoints;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Channel create(@RequestBody Channel channel) {
        return channelService.create(channel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Channel>> showChannelList() {
        final List<Channel> t = channelService.findAll();
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/join", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Channel subscribe(@PathVariable(value = "id") String id, @RequestBody Account account) {
        return channelService.join(id, account);
    }

    @RequestMapping(value = "/{id}/quit", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void unsubscribe(@PathVariable(value = "id") String id, @RequestBody Account account) {
        channelService.exit(id, account);
    }
}
