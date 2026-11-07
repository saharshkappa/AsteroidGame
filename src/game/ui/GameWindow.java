package game.ui;

import javax.swing.*;
import java.awt.*;
import game.model.GameState;

    public class GameWindow extends JFrame {
        private CardLayout cardLayout;
        private JPanel container;
        private GameState currentState;
        private GameMenu menu;
        private GamePanel panel;
        private GameDeath death;
        int score;

        public GameWindow() {

            super("Asteroid Game");
            cardLayout = new CardLayout();
            container = new JPanel(cardLayout);
            currentState = GameState.MENU;
            score=0;

            menu = new GameMenu(this);
            container.add(menu, "MENU");
            panel = new GamePanel(this);
            container.add(panel, "PLAYING");
            death = new GameDeath(this);
            container.add(death, "DEATH");

            pack();
            cardLayout.show(container, "MENU");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            add(container);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void toGamePanel() {
            currentState = GameState.PLAYING;
            resetScore();
            panel.resetGame();
            panel.startGame();
            cardLayout.show(container, "PLAYING");
            SwingUtilities.invokeLater(() ->
                    panel.requestFocusInWindow()
            );
        }

        public void toGameDeath() {
            currentState = GameState.DEATH;
            death.updateScore();
            panel.stopGame();
            cardLayout.show(container, "DEATH");
        }
        public void toGameMenu() {
            currentState = GameState.MENU;
            cardLayout.show(container, "MENU");
        }

        public int getScore(){ return score; }
        public void addScore(int points){ score += points; }
        public void resetScore(){ score = 0; }
}
