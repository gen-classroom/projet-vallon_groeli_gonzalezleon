# README

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
