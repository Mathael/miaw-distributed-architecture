package com.chattool.wrapper;

import com.chattool.model.Account;
import com.chattool.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leboc Philippe.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageWrapper {
    private Account account;
    private Message message;
}
