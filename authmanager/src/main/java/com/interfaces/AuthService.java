package com.interfaces;

/**
 * @author Leboc Philippe.
 */
public interface AuthService {
    // Authentication
    Object connect();
    void logout();
    Object register();

    // Save the data
    void save(Object discussion);
}
