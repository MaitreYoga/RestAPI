# RestAPI
Dépôt contenant l'API REST du serveur Glassfish (Servlets REST + BL)

L'API REST se met dans un projet "Dynamic Web Project" dans Eclipse, configuré pour lancer un serveur Glassfish


Pour lier un projet Eclipse avec le dépôt GIT :

1 - Cloner le dépôt dans le workspace d'Eclipse au nom "ZenLounge"

2 - Renommer temporairement le dossier créé

3 - Créer un "Dynamic Web Project" au nom "ZenLounge" (Penser à cocher "Generate web.xml deployment descriptor"))

4 - Copier le contenu du dossier renommé dans "ZenLounge"


Etapes complémentaires pour lancer le serveur :


1 - Placer les fichiers disponibles sur le dossier "lib" du Drive dans WEB-INF/lib/

2 - Dans les propriétés du projet, les librairies précédentes doivent être ajoutées (Libraries -> Add Jar)

3 - Dans les propriétés du serveur glassfish, cocher "Use Jar archives for deployment"