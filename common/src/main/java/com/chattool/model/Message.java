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
@XmlRootElement(name = "Message")
public final class Message implements Serializable {
    private String id;
    private String content;
    private Account account;
}
