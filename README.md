# Projet 2 : Jeu de balle

**Module : PROGRAMMATION JAVA AVANCÉE**

## Présentation

Jeu de balle en solo réalisé en Java avec une interface graphique Swing. Le joueur déplace une raquette en bas de la fenêtre pour renvoyer une balle qui rebondit sur les murs. La partie se termine lorsque les trois vies sont perdues.

## Fonctionnalités

- Déplacement de la raquette avec les flèches gauche et droite du clavier.
- Déplacement automatique de la balle avec rebonds sur les murs latéraux et le mur du haut.
- Détection des collisions entre la balle et la raquette.
- Score augmenté à chaque rebond sur la raquette.
- Augmentation progressive de la vitesse de la balle tous les 5 points.
- Système de 3 vies, avec relance de la balle après chaque perte.
- États de jeu : WAITING, RUNNING, PAUSED et GAME_OVER.
- Boutons de contrôle : START, PAUSE et RESTART.
- Écrans de démarrage, de pause et de fin de partie avec le score final.
- Boucle de jeu exécutée dans un thread dédié.

## Technologies utilisées

- Java
- Swing (`javax.swing`)
- AWT (`java.awt`, `java.awt.event`)

## Structure du projet

```
Projet_2_Jeu_de_balle/
├── src/
│   ├── JeuDeBalle.java
│   ├── GamePanel.java
│   └── GameController.java
└── README.md
```

- **JeuDeBalle** : classe principale. Elle crée la fenêtre, les boutons START, PAUSE et RESTART, l'affichage des informations des développeurs et de l'encadrant, puis lance le thread du jeu.
- **GamePanel** : panneau de jeu. Il contient les états du jeu, la raquette, la balle, le score, les vies, la logique de mise à jour (déplacements, collisions, vitesse), la boucle de jeu et l'affichage.
- **GameController** : contrôleur des interactions. Il traite les clics sur les boutons et les touches du clavier, puis met à jour l'état du `GamePanel`.

## Compilation et exécution

Prérequis : un JDK installé.

Depuis le dossier `Projet_2_Jeu_de_balle` :

```bash
cd src
javac JeuDeBalle.java GamePanel.java GameController.java
java JeuDeBalle
```

## Commandes de jeu

1. Cliquer sur **START** pour lancer la partie.
2. Déplacer la raquette avec les flèches gauche et droite.
3. Cliquer sur **PAUSE** pour mettre le jeu en pause, puis sur **START** pour reprendre.
4. Cliquer sur **RESTART** pour recommencer une nouvelle partie.

## Développeurs et encadrant

- Développé par : EL KIHEL Riyad et HALOUBI Ahmed
- Encadré par : M. Mustapha OUJAOURA - ENSA Safi
