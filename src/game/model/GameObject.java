package game.model;
import java.awt.Graphics2D;

public abstract class GameObject {
    protected double x, y, vx, vy, radius;
    boolean dead;
    GameObject(double x, double y, double vx, double vy, double radius){
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.radius=radius;
        dead=false;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }

    public abstract void update();
    public abstract void draw(Graphics2D g);

    public boolean collidesWith(GameObject object){
        double dist = Math.pow(x - object.x, 2) + Math.pow(y - object.y, 2);
        double sum =  radius + object.radius;
        return dist <= sum*sum;
    }
    public boolean isDead(){ return dead; }
    public void kill(){ dead=true; }



}
