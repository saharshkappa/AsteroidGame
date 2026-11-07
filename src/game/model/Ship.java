package game.model;

import java.awt.*;
import java.awt.geom.*;
import static game.util.Constants.SCREEN_HEIGHT;
import static game.util.Constants.SCREEN_WIDTH;

public class Ship extends GameObject{
    private final double SPEED = 3.5;
    public boolean movingRight;
    public boolean movingLeft;
    final double canonWidth = radius * 0.16;
    final double canonHeight = 1.15 * radius;
    final double paddingWidth = 2.5 * canonWidth;
    final double offset = 0.35 * radius;
    final double paddingHeight = 0.8 * canonHeight;

    public Ship(double x, double radius) {
        super(x, 0, 0, 0, radius);
        this.y = SCREEN_HEIGHT - 4 * radius ;
        movingRight = false;
        movingLeft = false;
    }
    public void setX(double x){this.x = x;}
    public void setRadius(double radius){this.radius = radius;}
    public double getBlasterXLeft(){return x + radius + paddingWidth/2 - canonWidth/2;}
    public double getBlasterXRight(){return x - radius - paddingWidth/2 - canonWidth/2;}
    public double getBlasterY(){return y - paddingHeight - canonHeight + offset;}
    public void setMovingRight(boolean b)   { movingRight = b; }
    public void setMovingLeft(boolean b) { movingLeft = b; }

    @Override
    public void update() {
        vx = 0;
        if (movingLeft)   vx -= SPEED;
        if (movingRight) vx += SPEED;
        x += vx;
        x = Math.clamp(x, radius + paddingWidth, SCREEN_WIDTH - radius - paddingWidth);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 0, 112));
        Shape circle = new Ellipse2D.Double(x - radius , y - radius , radius * 2, radius * 2);
        g.fill(circle);

//        double booster_rad = 1.25 * paddingWidth/ 2;
        g.setColor(Color.DARK_GRAY);
        g.fill(new Rectangle2D.Double(x - radius - paddingWidth/2 - canonWidth/2, y - paddingHeight - canonHeight + offset, canonWidth, canonHeight));
        g.fill(new Rectangle2D.Double(x + radius + paddingWidth/2 - canonWidth/2, y - paddingHeight - canonHeight + offset, canonWidth, canonHeight));
        g.setColor(new Color(227,100,47));
        g.fill(new Rectangle2D.Double(x - radius - paddingWidth, y - paddingHeight + offset, paddingWidth, paddingHeight));
        g.fill(new Rectangle2D.Double(x + radius, y - paddingHeight + offset, paddingWidth, paddingHeight));

//        g.draw(new Ellipse2D.Double(x - radius - paddingWidth/2 - booster_rad, y + offset, booster_rad * 2, booster_rad * 2));
//        g.draw(new Ellipse2D.Double(x + radius + paddingWidth/2 - booster_rad , y + offset, booster_rad * 2, booster_rad * 2));
    }
}