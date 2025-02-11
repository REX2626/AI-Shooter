import java.awt.Graphics;
import java.awt.Color;

public class Shooter {
    private int radius = 10;
    private int health = 100;
    private double rechargeTime = 0.3;
    private double timeRecharged = 0;
    private Color colour;

    private double x;
    private double y;
    private double rotation;

    public Shooter(double x, double y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public void shoot() {
        if (timeRecharged >= rechargeTime) {
            timeRecharged = 0;

            Constants.bullets.add(new Bullet(x + radius * Math.cos(rotation), y + radius * Math.sin(rotation), rotation));
        }
    }

    public void damage(int damage) {
        health -= damage;
        if (health < 0) {
            Constants.shooters.remove(this);
        }
    }

    public double distTo(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public int getRadius() {
        return radius;
    }

    public void pointTo(int x, int y) {
        rotation = Math.atan2(y - this.y, x - this.x);
    }

    public void moveUp() {
        y -= 0.5 / Constants.FPS;
    }

    public void moveDown() {
        y += 0.5 / Constants.FPS;
    }

    public void moveLeft() {
        x -= 0.5 / Constants.FPS;
    }

    public void moveRight() {
        x += 0.5 / Constants.FPS;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update() {
        timeRecharged += 1.0 / Constants.FPS;
    }

    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval((int) Math.round(x) - radius, (int) Math.round(y) - radius, radius * 2, radius * 2);

        // Draw gun pointing in direction of rotation
        g.setColor(new Color(50, 50, 50));
        g.drawLine((int) Math.round(x), (int) Math.round(y), (int) (x + Math.cos(rotation) * radius * 2), (int) (y + Math.sin(rotation) * radius * 2));
    }
}
