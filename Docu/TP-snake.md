# TP1 – SNAKE

## Objectif général  
Développer une application de jeu Snake :  
- Gestion des événements clavier  
- Gestion des collisions  
- Gestion du score  
- Mettre en pratique le paquet Swing  

---

## Partie 1 : Base de données et classe Utilisateur

### Étapes

#### 1.1 Créer un fichier `Snake.java`  
- Contient une seule classe `Snake` qui elle-même contient la méthode `main`  
- La méthode `main` instancie la classe jeu : `GameFrame`

#### 1.2 Créer un fichier `GameFrame.java`

```java
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame() {
        this.add(new GamePanel());
        this.setTitle("SNAKE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

#### 1.3 Créer un fichier `GamePanel.java`

```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    // À compléter
}

#### 1.4 Créer les attributs de la classe `GamePanel`

- Largeur et hauteur d’écran : **600 x 600**  
- L’écran est divisé en pas de **25** pixels  
- Les coordonnées `x` et `y` de la tête du serpent sont des tableaux d’entiers de taille `600 / 25`  
- La taille initiale du serpent est de **6 cases** (de 25 pixels)  
- Une pomme est placée aux coordonnées `appleX` et `appleY`  
- Une variable `direction` prenant les valeurs `'R'`, `'L'`, `'U'`, `'D'` selon la direction du serpent  

---

### Méthodes de la classe `GamePanel`

| Méthode                      | Description                                                                                                                |
|------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| `startGame()`                | Initialise le jeu en positionnant une pomme au hasard sur le plateau et démarre un timer                                    |
| `paintComponent(Graphics g)` | Dessine à l’écran une pomme rouge (cercle), le serpent (n carrés verts) et affiche le score                                |
| `newApple()`                 | Calcule au hasard les coordonnées de la pomme (`appleX` et `appleY`)                                                      |
| `move()`                     | Gère le déplacement du serpent (`x` et `y`) en fonction de sa direction                                                   |
| `checkApple()`               | Gère le contact de la tête du serpent avec la pomme. Si les coordonnées sont identiques : la taille du serpent augmente de 1, le score augmente de 1, une nouvelle pomme est placée |
| `checkCollision()`           | Vérifie si la tête du serpent (`x[0]`, `y[0]`) entre en collision avec le corps (`x[i]`, `y[i]` avec `i` de 1 à taille du serpent). Vérifie aussi si la tête sort du terrain. En cas de collision, le flag `gameOver` est déclenché et le timer est arrêté |
| `actionPerformed(ActionEvent e)` | Invoquée lors d’un événement (ici par le timer). Appelle successivement `move()`, `checkApple()`, `checkCollision()`, puis `repaint()` pour rafraîchir l’écran |

---

### Sous-classe pour la gestion du clavier : `MyKeyAdapter`

```java
public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }
}
