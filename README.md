# miaw-architecture-distribuee
- Utiliser l'IDE de votre choix
- Installer le plugin de votre IDE pour lombok (voir le menu install du site) : https://projectlombok.org/
- Avoir maven afin de télécharger les dépendances du projet

# Glassfish
Glassfish est une serveur permettant de déployer une application Java. Il permet, entre autre, d'utiliser le protocol HTTP.
La mise en place de glassfish est primordiale pour l'exécution du projet. Pour ce faire il faut télécharger l'archive à cette adresse : http://download.oracle.com/glassfish/5.0/promoted/latest-glassfish.zip dont la source est https://javaee.github.io/glassfish/

# Créer un domain glassfish
Pour utiliser GlassFish il faut spécifier un domaine. Pas besoin d'en savoir plus, il suffit de suivre bêtement les étapes que j'ai énuméré ci-dessous :
- Utiliser l'éxecutable C:\glassfish3\bin\asadmin.bat (donc se rendre dans le dossier)
- Ouvrir une console à cet emplacement
- Se servir de la commande: create-domain
       avec pour arguments: --port 4848 --nopassword my-localhost
- Il va se passer des choses dans la console

Voici ce que m'affiche la console. Une fois que vous avez successfully, c'est bon !

Using port 4848 for Admin.<br>
Using default port 8080 for HTTP Instance.<br>
Using default port 7676 for JMS.<br>
Using default port 3700 for IIOP.<br>
Using default port 8181 for HTTP_SSL.<br>
Using default port 3820 for IIOP_SSL.<br>
Using default port 3920 for IIOP_MUTUALAUTH.<br>
Using default port 8686 for JMX_ADMIN.<br>
Using default port 6666 for OSGI_SHELL.<br>
Using default port 9009 for JAVA_DEBUGGER.<br>
Distinguished Name of the self-signed X.509 Server Certificate is:<br>
[CN=LAPTOP-CEVLIMD5,OU=GlassFish,O=Oracle Corporation,L=Santa Clara,ST=California,C=US]<br>
Distinguished Name of the self-signed X.509 Server Certificate is:<br>
[CN=LAPTOP-CEVLIMD5-instance,OU=GlassFish,O=Oracle Corporation,L=Santa Clara,ST=California,C=US]<br>
Domain my-localhost created.<br>
Domain my-localhost admin port is 4848.<br>
Domain my-localhost allows admin login as user "admin" with no password.<br>
Command create-domain executed successfully.<br>

# Intégration de GlassFish à intelliJ
- En haut à droite, de la fenêtre principale de l'IDE, deployer le menu "Run/debug" (image ici: https://www.jetbrains.com/help/img/idea/2017.1/ij_runConfigMenu.png) et cliquer sur "Edit configuration".
- Utiliser le raccourci: ALT+INSERT à l'intérieur de cette fenêtre pour ouvrir un menu spéciale et choisir "GlassFish Server" puis "Local".
    - NB: Si "GlassFish Server" n'apparait pas, il faut alors aller en bas de ce menu et cliquer sur "More..."
- Une fois devant la fenêtre de configuration de GlassFish, il faut en premier lieu spécifier le champ "Application Server". Si votre installation de GlassFish n'est pas présente dans le menu déroulant, il faut alors cliquer sur le bouton permettant d'aller chercher l'installation sur le disque.
- Dans JRE il faut mettre le JDK 1.8 minimum !
- Dans Server Domain il faut maintenant choisir dans la liste déroulante le domaine créé à l'étape : #Créer un domain glassfish.
- Bien entendu, il faut désormais généré l'Artifact et le WAR. L'IDE propose tout il suffit de cliquer sur les boutons...
