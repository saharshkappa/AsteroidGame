package game.ui;

import game.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel implements ActionListener {
    private GameWindow gameWindow;

    public GameMenu(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setFocusable(true);
        JLabel title = new JLabel("* ASTEROID GAME *");
        title.setFont(new Font("Monospaced", Font.BOLD, 72));
        title.setForeground(Color.WHITE);
        JButton but = new JButton("Start Game");
        but.setPreferredSize(new Dimension(240, 50));
        but.setMaximumSize(new Dimension(240, 50));
        but.setBackground(new Color(30, 30, 30));
        but.setForeground(Color.WHITE);
        but.setFont(new Font("Monospaced", Font.BOLD, 16));
        but.addActionListener(this);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        but.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(but);
        add(Box.createVerticalGlue());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        gameWindow.toGamePanel();
    }

}
