package com.chattool.endpoints;

import com.chattool.model.Message;
import com.chattool.services.local.ChatService;
import com.chattool.wrapper.SendMessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
@RestController
@RequestMapping(value = "/chat", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean sendMessage(@RequestBody SendMessageWrapper wrapper) {
        return chatService.say(wrapper.getAccount(), wrapper.getMessage());
    }

    @RequestMapping(value = "/{channelId}", method = RequestMethod.GET)
    public List<Message> getChannelMessages(@PathVariable("channelId") String channelId) {
        Assert.hasLength(channelId, "channelId is null or empty");
        return chatService.getMessages(channelId);
    }

    @RequestMapping(value = "/{channelId}/{lastread}", method = RequestMethod.GET)
    public List<Message> getChannelMessagesFromLastRead(@PathVariable("channelId") String channelId, @PathVariable("lastread") String lastread) {
        Assert.hasLength(channelId, "channelId is null or empty");
        Assert.hasLength(lastread, "lastReadMessageId is null or empty");
        return chatService.getMessages(channelId, lastread);
    }
}
