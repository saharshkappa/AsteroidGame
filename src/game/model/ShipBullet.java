package game.model;

import java.awt.*;
import java.awt.geom.*;
import static game.util.Constants.SCREEN_HEIGHT;
import static game.util.Constants.SCREEN_WIDTH;

public class ShipBullet extends GameObject{
    private final double LENGTH = 30;
    private final double WIDTH = 3;
    final double SPEED = 8;


    public ShipBullet(double x, double y ){
        super(x,y,0, 0,3);
        this.vy=SPEED;
    }

    public boolean isOutside(){
        if(y < -LENGTH)
            return true;
        return false;
    }

    @Override
    public void update() {
        y -= vy;
        if(isOutside()) kill();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(new Rectangle2D.Double(x,y ,WIDTH, LENGTH));
    }
}
