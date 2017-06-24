package com.chattool.commands;

/**
 * @author Leboc Philippe.
 */
public class HelpCommand implements ICommandHandler {

    private static final String[] COMMANDS = {
        "help"
    };

    @Override
    public boolean useCommand(String command, String... params) {

        LOGGER.info("---------------- Liste des commandes ----------------");
        LOGGER.info("help\t\t\t\t\t\t\t: voir cette aide");
        LOGGER.info("login <username> <password>\t\t: Connexion au web service");
        LOGGER.info("register <username> <password>\t: Création d'un compte");
        LOGGER.info("logout\t\t\t\t\t\t\t: Déconnexion : Not implemented.");
        LOGGER.info("exit\t\t\t\t\t\t\t: Terminer le programme");
        LOGGER.info("list\t\t\t\t\t\t\t: Voir la liste des cannaux de discussion");
        LOGGER.info("create <name>\t\t\t\t\t: Créer un canal de discussion");
        LOGGER.info("join <name>\t\t\t\t\t\t: Rejoindre un canal de discussion");
        LOGGER.info("quit\t\t\t\t\t\t\t: Quitter le cannal en actif");
        LOGGER.info("say <message>\t\t\t\t\t: Ecrire un message dans le canal actif");
        LOGGER.info("online-list\t\t\t\t\t\t: Affichage la liste des utilisateurs connectés");
        LOGGER.info("online-friends\t\t\t\t\t: Affichage la liste des amis connectés");
        LOGGER.info("-----------------------------------------------------");

        return true;
    }

    @Override
    public String[] getCommandList() {
        return COMMANDS;
    }
}
