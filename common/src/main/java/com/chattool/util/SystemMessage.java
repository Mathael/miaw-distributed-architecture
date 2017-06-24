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
    public static final String UNKNOWN_COMMAND = "Commande inconnue. Veuillez reessayer";
    public static final String YOU_ARE_NOT_CONNECTED = "Vous n'êtes pas connecté";
    public static final String BAD_PARAMETERS = "Nombre de paramètres incorrect";

    // Messages commands
    public static final String YOU_MUST_WRITE_A_MESSAGE = "Vous devez spécifier un message";
    public static final String YOU_MUST_JOIN_A_CHANNEL_TO_WRITE_A_MESSAGE = "Vous devez rejoindre un cannal pour écrire un message";
    public static final String THE_MESSAGE_WASNT_SENT = "Le message n'a pas pu être envoyé";

    // Channel commands
    public static final String CHANNEL_NOT_CREATED = "Le cannal n'a pas été créé !";
    public static final String CHANNEL_CREATED_SUCCESSFUL = "Le cannal à été créé avec succès !";
    public static final String YOU_MUST_LEAVE_THE_CURRENT_CHANNEL = "Vous devez quitter le canal courant";
    public static final String NO_CHANNEL_FOUND = "Aucun canal de discussion n'est disponible";
    public static final String JOIN_FAILED = "L'action a échoué";
    public static final String YOU_MUST_JOIN_A_CHANNEL = "Vous vous devez rejoindre un canal pour effectuer cette action";
}
