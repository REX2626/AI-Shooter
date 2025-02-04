import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooter");
        frame.setSize(Constants.WIDTH, Constants.HEIGHT + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Shooter shooters[] = new Shooter[Constants.NUM_SHOOTERS];

        // Add player
        shooters[0] = new Shooter(Constants.WIDTH / 2, Constants.HEIGHT / 2, new Color(0, 0, 255));

        // Add enemies
        for (int i = 1; i < shooters.length; i++) {
            shooters[i] = new Shooter((int) (Math.random() * Constants.WIDTH), (int) (Math.random() * Constants.HEIGHT),
                    new Color(255, 0, 0));
        }

        DrawingPanel panel = new DrawingPanel(shooters);
        frame.add(panel);

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

                // Update
                for (int k = 0; k < shooters.length; k++) {
                    shooters[k].update();
                }

                frame.repaint();
            }

        }
    }

    static class DrawingPanel extends JPanel {
        Shooter shooters[];

        public DrawingPanel(Shooter shooters[]) {
            this.shooters = shooters;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Shooter shooter : shooters) {
                shooter.draw(g);
            }
        }
    }
}
