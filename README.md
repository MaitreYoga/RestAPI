# RestAPI
Dépôt contenant l'API REST du serveur Glassfish (Servlets REST + BL)

L'API REST se met dans un projet "Dynamic Web Project" dans Eclipse, configuré pour lancer un serveur Glassfish


Pour lier un projet Eclipse avec le dépôt GIT :

1 - Cloner le dépôt dans le workspace d'Eclipse

2 - Renommer temporairement le dossier créé

3 - Créer un "Dynamic Web Project" du même nom que le dépôt

4 - Copier le contenu du dossier renommé dans le projet Eclipse


Etapes complémentaires pour lancer le serveur :


1 - Placer les fichiers "commons-codec-1.10.jar" et "mysql-connector-java-5.1.34-bin.jar" disponibles sur le Drive dans WEB-INF/lib/

2 - Dans les propriétés du projet, les deux librairies précédentes doivent être ajoutées (Libraries -> Add Jar)
