package game.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static game.util.Constants.SCREEN_WIDTH;

public class Asteroid extends GameObject{
    public boolean movingUp;

    public Asteroid(double x, double y, double vx, double vy, double radius) {
        super(x, y, vx, vy, radius);

    }

    public boolean isOutside(){
        if(y > SCREEN_WIDTH + radius)
            return true;
        return false;
    }

    @Override
    public void update() {
        y += vy;
        if(isOutside()) kill();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        Shape circle = new Ellipse2D.Double(x - radius  , y - radius  , radius * 2, radius * 2);
        g.fill(circle);
        double resize = 2.5;
        int offset =2;
        g.setColor(Color.DARK_GRAY);
        g.fill(new Ellipse2D.Double(x - 2.5 * radius / (resize + offset), y - 2 * radius / (resize + offset) , radius / resize, radius / resize));
        g.fill(new Ellipse2D.Double(x + radius / (resize + offset), y - 2.4 * radius / (resize + offset) , radius / resize, radius / resize));
        g.fill(new Ellipse2D.Double(x + 1.2 * radius / (resize + offset), y + radius / (resize + offset) , radius / resize, radius / resize));
    }
}
