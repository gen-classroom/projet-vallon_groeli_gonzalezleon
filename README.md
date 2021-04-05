# README

[![Java CI with Maven](https://github.com/gen-classroom/projet-vallon_groeli_gonzalezleon/actions/workflows/maven.yml/badge.svg)](https://github.com/gen-classroom/projet-vallon_groeli_gonzalezleon/actions/workflows/maven.yml)

[![CI picocli](https://github.com/gen-classroom/projet-vallon_groeli_gonzalezleon/actions/workflows/commandLineTests.yml/badge.svg)](https://github.com/gen-classroom/projet-vallon_groeli_gonzalezleon/actions/workflows/commandLineTests.yml)

Ce projet créé par le groupe vallon_groeli_gonzalezleon a été créé sur github et contient 4 outil Picocli.

1. Build

2. Clean

3. Serve 

4. new


# Utilisation
Depuis le shell de git, exécuter les commandes suivantes (attention a bien être dans le bon dossier, celui qui contient le pom.xml)
```
mvn clean install \
    && rm -fr statique \
    && unzip target/statique.zip
```
Puis passez dans powershell et executez la commande suivante : 
```
 $env:Path += ";VotrePathVersLeDossierbinQueVousAvezExtraitdansStatique"
 ```
 Si vous rajoutez directement cette adresse dans le PATH de votre pc, vous avez plus besoin de faire cette partie.

Après vous pouvez appeler l'application avec statique.bat

Si vous voulez appeler une des sous commandes il faut les mettre derrière : statique.bat clean par exemple.

# Exemple d'utilisation

Notre application statique permet, en utilisant 3 commandes, d'initiliser, créer et de gestioner l'état de son site statique. Notre application est composée d'une commande principale, **statique**, et de 3 sous commandes, **init**, **build** et **clean**. Le fonctionnement de ces commandes est expliqué ci-dessous.

Un exemple d'utilisation de notre application est le suivant : 

1. Créer le dossier contenant le fichier markdown et json contenant les informations de la page principale en utilisant la commande init. Par exemple utiliser *statique init /mon/site*.

2. Modifier les fichiers markdown et/ou json, pour ajouter du contenu au site et modifier son header.
3. Construire le site en utilisant la commande *statique build*. Le document html crée sera placé dans le dossier build.

A partir de ce point, si l'utilisateur est satisfait du site, il n'as plus qu'a utiliser le fichier html produit et le mettre dans son serveur html. Si en revanche il veut rajouter plus de contenu, ou modifier le contenu déjà existant : 

4. Il peut modifier son site en modifiant le ficheir markdown et/ou json. Puis, avant de reutiliser la commande build, il doit utiliser *statique clean* pour enlever les fichiers contenu dans le dossier build.

Ce cycle peut être répété autant que voulu.
