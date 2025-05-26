import java.awt.*;                  // Pour les couleurs, dessin, polices...
import java.awt.event.*;           // Pour gérer les événements (clavier, timer)
import java.util.Random;              // Pour JPanel, JFrame, etc.
import javax.swing.*;           // Pour générer des positions aléatoires

public class GamePanel extends JPanel implements ActionListener {

    // Dimensions de l'écran de jeu
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    // Taille d'une "case" (unité de déplacement du serpent)
    static final int UNIT_SIZE = 25;

    // Nombre total de cases possibles
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    // Vitesse du jeu (plus petit = plus rapide)
    static final int DELAY = 75;

    // Coordonnées du serpent (x[0], y[0] = tête)
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // Longueur initiale du serpent
    int bodyParts = 6;

    // Score
    int applesEaten;

    // Position de la pomme
    int appleX;
    int appleY;

    // Direction actuelle du serpent ('R', 'L', 'U', 'D')
    char direction = 'R';

    // Le jeu est-il en cours ?
    boolean running = false;

    // Timer pour gérer le rythme du jeu
    Timer timer;

    // Générateur aléatoire pour la pomme
    Random random;

    // Constructeur du panneau de jeu
    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter()); // Gère les touches du clavier
        startGame(); // Démarre le jeu
    }

    // Initialisation du jeu
    public void startGame() {
        newApple();             // Place la première pomme
        running = true;         // On commence à jouer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // Gère le dessin à chaque rafraîchissement
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // Appelle notre méthode de dessin
    }

    // Dessin de la grille, serpent, pomme, score, etc.
    public void draw(Graphics g) {
        if (running) {
            // Optionnel : grille de fond
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.setColor(Color.darkGray);
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            // Dessine la pomme
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Dessine le serpent
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green); // Tête
                } else {
                    g.setColor(new Color(45, 180, 0)); // Corps
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Affiche le score en haut de l'écran
            g.setColor(Color.red);
            g.setFont(new Font("Poppins", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten,
                (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize()
            );

        } else {
            gameOver(g); // Fin du jeu
        }
    }

    // Génère une nouvelle pomme à un endroit aléatoire
    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    // Déplacement du serpent
    public void move() {
        // Déplacement du corps (de la fin vers la tête)
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Déplacement de la tête
        switch (direction) {
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
        }
    }

    // Vérifie si la tête touche la pomme
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    // Vérifie les collisions (avec soi-même ou les murs)
    public void checkCollisions() {
        // Tête touche corps
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // Tête touche les bords
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    // Affiche "Game Over" et le score
    public void gameOver(Graphics g) {
        // Score final
        g.setColor(Color.red);
        g.setFont(new Font("Poppins", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten,
            (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2,
            g.getFont().getSize());

        // Message "Game Over"
        g.setColor(Color.red);
        g.setFont(new Font("Poppins", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over",
            (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2,
            SCREEN_HEIGHT / 2);
    }

    // Méthode appelée automatiquement par le timer
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint(); // Rafraîchit l’écran
    }

    // Classe interne pour gérer les touches du clavier
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
}
