//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import javax.swing.*;
import game.model.Ship;
import game.model.Asteroid;
import game.model.ShipBullet;
import game.ui.GameWindow;
import game.util.Constants;

import java.util.*;

public class Main{
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });

    }
}
