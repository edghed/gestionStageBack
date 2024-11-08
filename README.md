# Back-End - Gestion des Stages et Étudiants

Ce projet back-end propose une API pour gérer les entités **Student** et **Stage** avec des fonctionnalités CRUD et une gestion des rôles. 

## Documentation OpenAPI
Pour accéder à la documentation complète de l'API OpenAPI : http://localhost:8080/v3/api-docs

## Authentification et Gestion des Rôles

- **Type d'authentification** : Token Authentication
- **Session** : Gestion de session stateless
- **Rôles disponibles** : 
  - `ROLE_ADMIN` : Permet un accès complet aux fonctionnalités CRUD sur les étudiants et stages, ainsi que la gestion des utilisateurs.
  - `ROLE_STUDENT` : Accès limité à la liste des stages avec la possibilité de consulter les détails.

Chaque token d'authentification contient le claim `role`, précisant le rôle de l'utilisateur.

## Entités Principales
1. **Student** : Gestion des étudiants inscrits dans le système.
2. **Stage** : Gestion des stages disponibles.

## Fonctionnalités
- CRUD complet sur les entités **Student** et **Stage**.
- Gestion des rôles :
  - **Admin** : Accès complet à toutes les fonctionnalités.
  - **Student** : Accès limité aux fonctionnalités de consultation de stages.

## Base de Données
- **Base de données temporaire** : H2
- **Utilisateur par défaut** : 
  - **Username** : `admin`
  - **Mot de passe** : `admin`


