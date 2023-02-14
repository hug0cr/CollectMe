# CollectMe

Combien y a-t-il de personnes qui se demandent s'ils ont déjà acheté ce roman,
mangas, CD ou ce jeu vidéo et qui, parfois, finissent par l’acheter de nouveau et se
retrouve avec deux exemplaires du même article.

CollectMe est un application Android qui répond à ce besoin.

## Utiliser ce projet

1. Cloner le repo
2. Créer un projet Firebase
3. Ajouter une application Android au projet avec comme package fr.hug0cr.collectme
4. Copier le fichier google-services.json dans le dossier /app (fichier à ne pas commiter)
5. Dans la console Firebase, créer une authentification email/motde passe avec un provider Anonymous
6. Créer une BDD Firestore avec comme règle de sécurité :

```
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId}/{document=**} {
      allow read, write : if request.auth.uid == userId;
    }
  }
}
``` 
7. Exécuter l'app
