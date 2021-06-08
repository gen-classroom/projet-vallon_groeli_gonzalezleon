# Site statique : manuel utilisateur 



## Installation

Dans un premier temps, télécharger le fichier zip du projet de Github. Extraire le projet et le placer dans un emplacement au choix. Se placer ensuite dans le dossier au niveau de "projet-vallon_groeli_gonzalezleon-master/projet-vallon_groeli_gonzalezleon-master/SiteStatique"  (s'assurer que ce soit bien le dossier qui contient le pom.xml) et exécuter les commandes suivantes dans le shell de git :

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

**Si vous voulez appeler une des sous commandes il faut les mettre derrière l'appel à statique.bat : statique.bat clean par exemple.**

- `statique -v` ou `statique --version` : afficher la version du générateur de site statique 
- `statique -h` ou `statique --help` :  pour obtenir de l'aide sur les commandes init, build, clean et serve. 
- `statique init <nom dossier>` : créer le dossier de base contenant le fichier index markdown et le fichier de configuration json dans un sous dossier. 
- `statique init -m <nom dossier>` ou `statique init --markdown <nom dossier>`  : génère simplement un nouveau fichier index markdown dans un dossier déjà existant.
- `statique build` : construire le site statique et générer le dossier build
- `statique build-p=<path>` : construire le site statique à un vers un chemin donné 
- `statique clean` : supprime le dossier build
- `statique serve` : ouvre un navigateur pour visualiser le résultat du site statique

## Exemple d'utilisation

L'application statique permet, en utilisant 4 commandes,  d'initialiser, de créer, de gérer et de visualiser l'état du site statique. L'application est donc composée d'une commande principale, **statique**, et de 4 sous commandes principales, **init**, **build**, **clean** et **serve**. 

Un exemple d'utilisation de notre application est le suivant :

1. Créer le dossier contenant le fichier markdown et json contenant les informations de la page principale en utilisant la commande init. Par exemple utiliser `statique init /mon/site`.

Résultat obtenu dans le terminal Window :

![image-20210604113758748](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210604113758748.png)

Dans le répertoire /mon/site on obtient le résultat suivant :

![image-20210604113925125](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210604113925125.png)

Le dossier template contient le fichier layout.html.

2. Modifier les fichiers markdown et/ou json, pour ajouter du contenu au site et modifier son header.

Dans l'exemple qui suit on modifie le fichier index.md. Se référer à la section plus bas pour les différents fichiers modifiables et les différentes parties du fichier qui sont modifiables par l'utilisateur. 

![image-20210604114430688](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210604114430688.png)

3. Construire le site en utilisant la commande `statique build`. Le document html index.html créé sera placé dans un dossier build lui aussi nouvellement créé.

![image-20210604114544820](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210604114544820.png)

On obtient donc le répertoire suivant :

![image-20210608095001578](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210608095001578.png)



4. Visualiser le résultat dans le navigateur web par défaut avec la commande *statique serve*, pour observer le résultat final après avoir effectué d'éventuelles modifications :

![image-20210608095545421](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210608095545421.png)

5. Nettoyer le projet avec `statique clean`, c'est-à-dire supprimer le dossier build pour pouvoir le reconstruire entièrement par la suite en relançant un build (pouvoir par exemple visualiser le résultat des modifications).
6. L'utilisateur peut choisir de générer plusieurs pages html. Pour cela il faut créer un nouveau répertoire à la base du projet créé par init et lancer la `commande statique init -m <nom répertoire créé>` pour créer un nouveau fichier markdown. L'utilisateur peut modifier ce fichier à sa convenance puis lancer un clean suivi d'un build pour construire la sous page. 

![image-20210608102854176](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210608102854176.png)

Dans le dossier build on obtient un nouveau dossier contenant la page html :

![image-20210608102922850](C:\Users\jaden\AppData\Roaming\Typora\typora-user-images\image-20210608102922850.png)

La commande serve ne permet de visualiser uniquement le résultat de la page principale. Pour visualiser le résultat des sous-pages, il est nécessaire de créer des liens depuis la page principale vers les sous-pages.

7. A partir de ce point, dès que le résultat est satisfaisant, il n'y a plus qu'à utiliser le résultat html produit et le mettre dans un serveur html. 

Ce cycle peut être répété autant que voulu.

## Fichiers modifiables :

Pour personnaliser le site statique, l'utilisateur peut modifier les fichiers suivants :

- tous les fichiers de type markdown : **attention** tous les fichiers de ce type doivent avoir la même structure : même titres de header suivi des 3 tirets après un retour à la ligne puis un autre retour à la ligne avec le contenu du site qui varie d'un fichier à l'autre. Le fichier markdown de la page principal doit obligatoirement garder le nom index.md mais tous les autres fichiers md qui sont créé sont nommés au choix par l'utilisateur. Les autres fichiers servent à définir les sous-pages du site. Pour chaque fichier markdown produit, une page html sera créé. 
- config.json : les valeurs de charset, keywords, siteTitle, domain et language peuvent soit être modifiés, soit être supprimés. Attention à bien entourer les valeurs de " ".
- template/layout.html : l'utilisateur peut modifier ou supprimer les valeurs entre triple accolades ou en ajouter. Les valeurs entre triple accolades doivent obligatoirement correspondre au titre d'une valeur du fichier config.json ou à un titre du header des fichiers du type markdown. 

