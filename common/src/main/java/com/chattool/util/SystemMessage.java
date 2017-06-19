package com.chattool.util;

/**
 * @author Leboc Philippe.
 */
public class SystemMessage {
    // RMI
    public static final String RMI_REMOTE_FAIL = "RMI Remote exception";

    // Registry
    public static final String REGISTRY_LOCATE = "Récupération du registre";
    public static final String REGISTRY_CANNOT_BE_LOCATED = "Cannot locate registry";

    // Login
    public static final String LOGIN_REQUEST_FAIL = "Login request has failed";
    public static final String LOGIN_REQUEST_SUCCESS = "Login request was successful";

    // Register
    public static final String REGISTER_FAIL = "Register request has fail";
    public static final String REGISTRATION_FAIL_ILLEGAL_CHARACTER = "Registration fail due to illegal use of character ':'";
    public static final String REGISTER_SUCCESS = "Register request was successful";

    // File
    public static final String FILE_EXCEPTION = "File manipulation exception spotted";
    public static final String FILE_EXCEPTION_READ_FILE = "Unable to open the account file";

    // Account
    public static final String CANNOT_RETRIEVE_ACCOUNT = "Cannot retrieve account";

    // Commands
    public static final String UNKNOWN_COMMAND = "Commande inconnue. Veuillez reessayer.";
    public static final String COMMAND_WRONG_PARAMETERS = "Le nombre de paramètres ou le format n'est pas valide";
}
