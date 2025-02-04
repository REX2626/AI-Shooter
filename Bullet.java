import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class Bullet {
    private double x;
    private double y;
    private double rotation;
    private int speed = 200;

    public Bullet(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public void update() {
        x += Math.cos(rotation) * speed / Constants.FPS;
        y += Math.sin(rotation) * speed / Constants.FPS;

        if (x < 0 || x > Constants.WIDTH || y < 0 || y > Constants.HEIGHT) {
            Constants.bullets.remove(this);
        }

        List<Shooter> shootersCopy = new ArrayList<Shooter>(Constants.shooters);
        for (Shooter shooter : shootersCopy) {
            if (shooter != null && shooter.distTo(x, y) < shooter.getRadius()) {
                Constants.bullets.remove(this);
                shooter.damage(40);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillOval((int) Math.round(x) - 2, (int) Math.round(y) - 2, 4, 4);
    }
}
