# Arista
Ce projet est réalisé dans le cadre du cours OPENCLASSROOMS - Developpers Android

Sur la base d'une application existante "Arista" destinée à suivre les données personnelles de l'utilisateur telles que les exercices physiques et les cycles de sommeil,
l'objectif est de mettre en place une base de données locales en utilisant Room.


## Fonctionnalités déjà installées
- Enregistrement des exercices physiques de l'utilisateur : durée, intensité, catégorie.
- Suivi du sommeil : heure de début, durée, qualité du sommeil.
- Profil utilisateur : informations personnelles comme le nom, l'email, le mot de passe.

## Réalisations pour ce projet
Data :
 - ajout des entités, des data access, des mappers, du converters et de la base.
 - modifications des repositories

Domain :
 - ajout interface repository, modification des usecases

Di :
 - instance de la base, population de la base, instance Dao
 - modification de l'injection existante

Ui :
 - modification viewmodel
