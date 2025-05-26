# 🎯 Jeu Snake – Projet Java Swing

[![Java](https://skillicons.dev/icons?i=java)](https://www.java.com/)  
![Swing](https://img.shields.io/badge/Swing-GUI-yellow)
![Status](https://img.shields.io/badge/statut-fini-success)

Bienvenue dans ce projet de développement d’un jeu **Snake** en Java avec **Swing**, réalisé dans le cadre de ma formation en développement fullstack Java.  
Ce projet met en pratique la gestion des événements clavier, les collisions, l’affichage graphique, et la structuration en classes.

---

## 📚 Sommaire

- [🚀 Présentation](#-présentation)
- [🕹️ Fonctionnalités](#-fonctionnalités)
- [🛠️ Stack technique](#-stack-technique)
- [📦 Dépendances](#-dépendances)
- [🔮 Pistes d’amélioration](#-pistes-damélioration)
- [📸 Captures d’écran](#-captures-décran)
- [📌 Auteur](#-auteur)
- [📄 Licence](#-licence)

---

## 🚀 Présentation

Le **jeu Snake** est une application classique où le joueur contrôle un serpent qui se déplace sur une grille.  
Le but est de manger des pommes générées aléatoirement : chaque pomme mangée fait grandir le serpent.  
Le jeu se termine si le serpent heurte les murs ou se mord lui-même.

---

## 🕹️ Fonctionnalités

- ⌨️ **Gestion des événements clavier** (flèches directionnelles)  
- 🧱 **Gestion des collisions** (murs et corps du serpent)  
- 🧮 **Calcul du score** en fonction des pommes mangées  
- 🎨 **Affichage graphique** avec Swing (serpent, pomme, score)  
- ⏱️ **Boucle de jeu** via `Timer` (rafraîchissement automatique)  
- 🧩 **Architecture modulaire** avec classes distinctes :  
  - `Snake` (classe principale)  
  - `GameFrame` (fenêtre du jeu)  
  - `GamePanel` (logique graphique et gameplay)

---

## 🛠️ Stack technique

| Technologie              | Rôle                                            |
|-------------------------|-------------------------------------------------|
| Java 24                 | Langage principal                               |
| Swing                   | Interface graphique (fenêtres, dessin, events) |
| AWT                     | Gestion des événements clavier et graphiques    |
| Timer (`javax.swing`)   | Boucle de jeu automatisée                       |

---

## 📦 Dépendances

Aucune dépendance externe requise.  
Le projet s'appuie uniquement sur l’API standard de Java (`java.awt`, `javax.swing`, `java.util`).

---

## 🔮 Pistes d’amélioration

- 🏁 Ajout d’un **écran d’accueil** avec bouton "Jouer"  
- 🎚️ **Niveaux de difficulté** (vitesse croissante)  
- 💾 Sauvegarde du **meilleur score** localement  
- 🔊 **Effets sonores** (manger une pomme, game over)  
- 💡 Ajout d’un **mode sans murs** ou multi-pommes  
- 🎨 Animation du **Game Over** avec transition graphique  

---

## 📸 Captures d’écran

Voici quelques aperçus du jeu en fonctionnement :

![Capture écran Snake](./Docu/Capture/snake-game-screenshot1.png)  
*Vue principale du jeu avec serpent et pomme*

![Écran Game Over](./Docu/Capture/snake-game-over.png)  
*Écran affiché à la fin de la partie*

---

## 📌 Auteur

👨‍💻 **Quentin** – Étudiant développeur Fullstack Java  
🎓 Projet réalisé dans le cadre de ma formation (Simplon)  
🎯 Rôle principal : conception et développement complet (moteur de jeu, affichage graphique, gestion des entrées utilisateur)

📆 Projet solo – Mai 2025

---

## 📄 Licence

Ce projet est open-source sous licence **MIT**.  
Voir le fichier [LICENSE](./LICENSE) pour plus d’informations.

---
