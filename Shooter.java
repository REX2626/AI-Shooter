import java.awt.Graphics;
import java.awt.Color;

public class Shooter {
    private int radius = 10;
    private int health = 100;
    private double rechargeTime = 0.5;
    private double timeRecharged = 0;
    private Color colour;

    private int x;
    private int y;
    private double rotation;

    public Shooter(int x, int y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public void shoot() {
        if (timeRecharged >= rechargeTime) {
            timeRecharged = 0;
        }
    }

    public void moveUp() {
        y -= 1;
    }

    public void moveDown() {
        y += 1;
    }

    public void moveLeft() {
        x -= 1;
    }

    public void moveRight() {
        x += 1;
    }

    public void update() {
        timeRecharged += 1.0 / Constants.FPS;
    }

    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

        // Draw gun pointing in direction of rotation
        g.setColor(new Color(50, 50, 50));
        g.drawLine(x, y, (int) (x + Math.cos(rotation) * radius * 2), (int) (y + Math.sin(rotation) * radius * 2));
    }
}
