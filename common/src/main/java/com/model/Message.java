package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Lucas on 31/05/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Message {

    private String messageId;
    private String messageContent;
    private Account messageAccount;
}
