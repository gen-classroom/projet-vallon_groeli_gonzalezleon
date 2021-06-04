# Site statique : manuel utilisateur 

## Installation

Dans un premier temps,télécharger le fichier zip du projet de Github. Extraire le projet et le placer dans un emplacement au choix. Se placer ensuite dans le dossier au niveau de "projet-vallon_groeli_gonzalezleon-master/projet-vallon_groeli_gonzalezleon-master/SiteStatique"  (s'assurer que ce soit bien le dossier qui contient le pom.xml) et exécuter les commandes suivantes dans le shell de git :

```shell
mvn clean install \
    && rm -fr statique \
    && unzip target/statique.zip

```

Passer ensuite dans powershell et executez la commande suivante :

` $env:Path += ";VotrePathVersLeDossierbinQueVousAvezExtraitdansStatique"`

Si vous rajoutez directement cette adresse dans le PATH de votre pc, vous avez plus besoin de faire cette partie.

Après vous pouvez appeler l'application avec statique.bat

## Liste des commandes 

**Si vous voulez appeler une des sous commandes il faut les mettre derrière : statique.bat clean par exemple.**

- `statique -v` ou `statique --version` : afficher la version du générateur de site statique 
- `statique -h` ou `statique --help` :  pour obtenir de l'aide sur les commandes init, build, clean et serve. 
- `statique init <nom dossier>` : créer le dossier de base contenant le fichier index markdown et le fichier de configuration json dans un sous dossier. 
- `statique init -m <nom dossier>` ou `statique init --markdown <nom dossier>`  : génère simplement un nouveau fichier index markdown dans un dossier déjà existant.
- `statique build` : construire le site statique et générer le dossier build
- `statique build-p=<path>` : construire le site statique à un vers un chemin donné 
- `statique clean` : supprime le dossier build
- `statique serve` : ouvre un navigateur pour visualiser le résultat du site statique

## Exemple d'utilisation

L'application statique permet, en utilisant 4 commandes,  d'initialiser, de créer, de gérer et de visualiser l'état du site statique. L'application est donc composée d'une commande principale, **statique**, et de 4 sous commandes, **init**, **build**, **clean** et **serve**. 

Un exemple d'utilisation de notre application est le suivant :

1. Créer le dossier contenant le fichier markdown et json contenant les  informations de la page principale en utilisant la commande init. Par  exemple utiliser *statique init /mon/site*.
2. Modifier les fichiers markdown et/ou json, pour ajouter du contenu au site et modifier son header.
3. Construire le site en utilisant la commande *statique build*. Le document html créé sera placé dans le dossier build.
4. Visualiser le résultat dans le navigateur web par défaut avec la commande *statique serve*, effectuer des modification 
5. Nettoyer le projet, c'est-à-dire supprimer le dossier build pour pouvoir le reconstruire entièrement en utilisant la commande *statique clean* (pouvoir par exemple visualiser le résultat des modifications). 

A partir de ce point, dès que le résultat est satisfaisant, il n'y a plus qu'à utiliser le fichier html produit et le mettre dans un serveur html. Si en revanche il faut rajouter plus de contenu, ou modifier le contenu déjà existant :

1. Modifier le site en modifiant le fichier markdown et/ou  json. Puis, avant de réutiliser la commande build, il faut utiliser *statique clean* pour enlever les fichiers contenu dans le dossier build.

Ce cycle peut être répété autant que voulu.

