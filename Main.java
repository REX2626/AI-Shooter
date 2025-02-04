import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooter");
        frame.setSize(Constants.WIDTH, Constants.HEIGHT + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add player
        Constants.shooters.add(new Shooter(Constants.WIDTH / 2, Constants.HEIGHT / 2, new Color(0, 0, 255)));

        // Add enemies
        for (int i = 1; i < Constants.NUM_SHOOTERS; i++) {
            Constants.shooters.add(new Shooter((int) (Math.random() * Constants.WIDTH), (int) (Math.random() * Constants.HEIGHT),
                    new Color(255, 0, 0)));
        }

        DrawingPanel panel = new DrawingPanel();
        frame.add(panel);
        frame.setVisible(true);

        // Generations
        for (int i = 0; i < Constants.NUM_GENERATIONS; i++) {

            // 600 ticks per walker
            for (int j = 0; j < 600; j++) {
                // Ensure 60 FPS
                try {
                    Thread.sleep(1000 / Constants.FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Rotate player based on mouse position
                Point mouse = MouseInfo.getPointerInfo().getLocation();
                Constants.shooters.get(0).pointTo((int) mouse.getX(), (int) mouse.getY());

                // Move player based on their key presses
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getID() == KeyEvent.KEY_PRESSED) {
                            if (e.getKeyCode() == KeyEvent.VK_W) {
                                Constants.shooters.get(0).moveUp();
                            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                                Constants.shooters.get(0).moveDown();
                            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                                Constants.shooters.get(0).moveLeft();
                            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                                Constants.shooters.get(0).moveRight();
                            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                                Constants.shooters.get(0).shoot();
                            }
                        }
                        return false;
                    }
                });

                // Update shooters
                List<Shooter> shootersCopy = new ArrayList<Shooter>(Constants.shooters);
                for (Shooter shooter : shootersCopy) {
                    shooter.update();
                }

                // Update bullets
                List<Bullet> bulletsCopy = new ArrayList<Bullet>(Constants.bullets);
                for (Bullet bullet : bulletsCopy) {
                    bullet.update();
                }

                frame.repaint();
            }

        }
    }

    static class DrawingPanel extends JPanel {

        public DrawingPanel() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Shooter shooter : Constants.shooters) {
                shooter.draw(g);
            }

            for (Bullet bullet : Constants.bullets) {
                bullet.draw(g);
            }
        }
    }
}
