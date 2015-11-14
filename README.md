# RestAPI
Dépôt contenant l'API REST du serveur Glassfish (Servlets REST + BL)

L'API REST se met dans un projet "Dynamic Web Project" dans Eclipse, configuré pour lancer un serveur Glassfish

Dans un premier temps, téléchargez le serveur payara-4.1.1

Pour lier un projet Eclipse avec le dépôt GIT :

1 - Cloner le dépôt dans le workspace d'Eclipse au nom "ZenLounge"

2 - Renommer temporairement le dossier créé

3 - Créer un "Dynamic Web Project" au nom "ZenLounge" (Penser à cocher "Generate web.xml deployment descriptor"))

4 - Copier le contenu du dossier renommé dans "ZenLounge"


Etapes complémentaires pour lancer le serveur :


1 - Placer les fichiers disponibles sur le dossier "lib" du Drive dans WEB-INF/lib/
    (common-codec-1.10.jar et mysql-connector-java-5.A.34-bin.jar)

2 - Dans les propriétés du projet, les librairies précédentes doivent être ajoutées (Libraries -> Add Jar)

3 - Supprimmez les anciens serveurs, windows, preferences, server, runtime environment.

3 - Créez votre serveur (clic droit new serveur, glassfish 4, donner la localisation du domaine 1 du serveur payara), cocher "Use Jar archives for deployment"

4 - Dans les propriétés du projets, Server choisissez votre serveur glassfish

5 - Clic doit sur le projet, Projects Facets, rajouter JPA, cliquez sur "Further configuration available"
    Choisissez EclipseLink 2.5 puis cliquez sur add connection, Mysql, configurer avec les logins mdp de la base,
    Testez la connection Cliquez sur "Finish". Cochez ensuite "Override default schema from connection". 
    Cliquez sur ok.

6 - Une fois le webServer installé, avec votre navigateur, allez sur localhost:4848. (pb avec IE)
    Créez une nouvelle "connection pool". (amazonePool, Datasource, mysql puis next). Activez le ping, remplissez les champs user, DataBaseName, password, URL(en majuscule) après les deux / mettre le nom d'hôte de la base, rajoutez le nom de la base à la fin du champs (après le dernier /). Cliquez sur finish.

7 - Créez une nouvelle ressource JDBC, name jdbc/amazone, poolName amazonePool. Cliquez sur ok'