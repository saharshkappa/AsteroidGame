package game.ui;

import game.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDeath extends JPanel implements ActionListener{
    private GameWindow gameWindow;
    JButton but;
    JButton replay;
    private JLabel score;

    public GameDeath(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 0, 0, 160));
        setFocusable(true);

        JLabel l = new JLabel("GAME OVER!");
        score = new JLabel("Your Score: " + gameWindow.getScore());
        l.setFont(new Font("Monospaced", Font.BOLD, 48));
        l.setForeground(Color.RED);
        score.setFont(new Font("Monospaced", Font.BOLD, 32));
        score.setForeground(Color.WHITE);
        JButton but = new JButton("Return to Game Menu");

        but.setPreferredSize(new Dimension(240, 50));
        but.setMaximumSize(new Dimension(240, 50));
        but.setBackground(new Color(128, 30, 30));
        but.setForeground(Color.WHITE);
        but.setFont(new Font("Monospaced", Font.BOLD, 16));
        but.addActionListener(this);

        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        but.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(l);
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(score);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(but);
        add(Box.createVerticalGlue());
    }

    public void updateScore(){
        score.setText("Your Score: " + gameWindow.getScore());
    }

    @Override
    public void actionPerformed(ActionEvent e){
//        if (e.getSource() == but)
            gameWindow.toGameMenu();
    }
}
