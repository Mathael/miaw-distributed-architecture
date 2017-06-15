package com.chattool.endpoints;

import com.chattool.services.ChatService;
import com.chattool.wrappers.SendMessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
