import javax.swing.JFrame;

public class GameFrame extends JFrame {

    // Constructeur : définit tout ce qui concerne la fenêtre principale
    GameFrame() {
        this.add(new GamePanel()); // Ajoute notre panneau de jeu (où tout se dessine)

        this.setTitle("SNAKE"); // Nom affiché en haut de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application quand on clique sur la croix
        this.setResizable(false); // Empêche de redimensionner la fenêtre (important pour la logique du jeu)
        this.pack(); // Adapte automatiquement la taille de la fenêtre au contenu (ici GamePanel)
        this.setLocationRelativeTo(null); // Centre la fenêtre au milieu de l'écran
        this.setVisible(true); // Rend la fenêtre visible (indispensable !)
    }
}
