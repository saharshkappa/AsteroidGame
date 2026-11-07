package game.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import game.model.Asteroid;
import game.model.Ship;
import game.model.ShipBullet;
import game.util.AsteroidSpawner;
import game.util.Constants;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements KeyListener {
    private final GameWindow gameWindow;
    private Ship ship;
    private final List<Asteroid> asteroids;
    private final List<ShipBullet> bullets;
    private final AsteroidSpawner spawner;
    private long lastShootTime;
    private final int shootInterval;
    private Timer timer;
    private BufferedImage background;

    public GamePanel(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        asteroids = new ArrayList<>();
        bullets = new ArrayList<>();
        ship = new Ship(300, 25);
        spawner = new AsteroidSpawner(800);
        shootInterval = 400;
        lastShootTime = -shootInterval;

        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        try {
            background = ImageIO.read(
                    getClass().getResource("/res/background.png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer(16, e -> {
            update();
            repaint();
        });
    }

    private void update(){

        Asteroid temp_asteroid = spawner.trySpawn();
        if(temp_asteroid != null) asteroids.addLast(temp_asteroid);
        ship.update();
        for(ShipBullet bullet : bullets)
            bullet.update();
        for (Asteroid asteroid  : asteroids) {
            asteroid.update();
            for(ShipBullet bullet : bullets){
                if(asteroid.collidesWith(bullet)){
                    asteroid.kill();
                    bullet.kill();
                    gameWindow.addScore(1);
                    break;
                }
            }
            if(asteroid.collidesWith(ship)) {
                System.out.println("you died");
                gameWindow.toGameDeath();
                return;
            }
        }

        asteroids.removeIf(Asteroid::isDead);
        bullets.removeIf(ShipBullet::isDead);
    }

    public void resetGame() {
        asteroids.clear();
        bullets.clear();
        ship = new Ship(300, 25);
        spawner.setLastSpawnTime(0);
        repaint();
    }

    public void startGame() {
        timer.start();
    }

    public void stopGame() {
        timer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("Key Pressed Code: " + e.getKeyCode());
        switch(e.getKeyCode()){
            case 65:
            case 37: ship.setMovingLeft(true); break;
            case 68:
            case 39: ship.setMovingRight(true); break;
            case 32:
            case 38:
            case 87:
            tryShoot(bullets);
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        System.out.println("Key Released Code: " + e.getKeyCode());
        switch(e.getKeyCode()){
            case 65:
            case 37: ship.setMovingLeft(false); break;
            case 68:
            case 39: ship.setMovingRight(false); break;
        }
    }

    private void drawScore(Graphics2D g){
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Monospaced", Font.BOLD, 12));
        String text = "SCORE: " + gameWindow.getScore();
        FontMetrics fm = g.getFontMetrics();
        int x = getWidth() - fm.stringWidth(text) - 20;
        int y = 30;

        g.drawString(text, x, y);
    }

    private void tryShoot(List<ShipBullet> bullets){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastShootTime >= shootInterval) {
            lastShootTime = currentTime;
            System.out.println("bullet");
            bullets.addLast(new ShipBullet(ship.getBlasterXLeft(), ship.getBlasterY()) );
            bullets.addLast(new ShipBullet(ship.getBlasterXRight(), ship.getBlasterY()) );
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if (background != null)
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        ship.draw(g2d);
        for (Asteroid asteroid  : asteroids) {
            asteroid.draw(g2d);
        }
        for(ShipBullet bullets : bullets){
            bullets.draw(g2d);
        }
        drawScore(g2d);
    }
}
