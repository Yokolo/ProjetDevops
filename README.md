Au cours de ce projet, nous avons utilisé Maven/Github/TravisCI ainsi que Junit pour faire nos tests.

Pour la connexion client serveur nous avons utilisé la librarie kryonet :
https://github.com/EsotericSoftware/kryonet

Vous trouverez dans notre librairie une interface client/serveur qui vous permet de stocker des valeurs sur le serveur.

**Listes des commandes utilisables par le client**
set key v1 -associe à la clef key la valeur v1
get key -récupère la valeur associée à la clef key
incr key [v1] -incrémente la valeur associée à la clef key(cette valeur doit être entiere pour que cela fonctionne ) de v1 (de 1 si v1 n'est pas spécifié)
setlist key v1 [v2..] - associe à la clef key la liste contenant v1 [v2..]. Ne peux pas crée une liste vide.
getlist key - récupère la liste associée à la clef key
listadd key v1 [v2..] - ajoute à la liste associée à la clef key la valeur v1 ( et v2.. si elles sont présentes)
listremove key v1 - Supprime la première occurence de la valeur v1 dans la liste associée à la clef key
help - Affiche l'aide de l'interface client.
quit - Ferme l'application
exit - Ferme l'application

Les types acceptés sont : double char string int boolean. A noté que pour l'incrémentation, celle ci ne marche que sur les entiers.

**Comment utiliser le client et le serveur** 

Vous trouverez dans l'archive deux scripts : runclient.sh et runserveur.sh

Pour lancer le serveur il suffit juste d'executer le script runserveur.sh et de le laisser tourner.
Ensuite vous pourrez vous connecter à ce serveur via le script runclient.sh.
A partir de maintenant, vous êtes connecté au serveur, vous pouvez utiliser les commandes cités dans la section d'avant.
Chaque client possède son espace de stockage.Toutes les fonctions de stockages ont passé les tests unitaires.


**FEEDBACK**:

Ce projet était très intéressant , il nous a permi de découvrir TRAVIS CI qui est vraiment plaisant pour tester en direct notre application lorsqu'on push sur github. 
De plus dans ce projet nous avons bien evidemment appris a utiliser Maven qui est un outil indispensable pour développer une application java.



