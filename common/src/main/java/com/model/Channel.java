package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 31/05/2017.
 */
@Data
@AllArgsConstructor
public final class Channel {

    private String channelId;
    private String channelName;
    private List<Message> channelMessageList;

    public Channel() {
        this.channelMessageList = new ArrayList<>();
    }

}
