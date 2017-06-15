package com.chattool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Lucas on 31/05/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public final class Message implements Serializable {
    private String id;
    private String channelId;
    private String content;
    private String author;

    public Message(String channelId, String content, String author){
        this("-1", channelId, content, author);
    }
}
