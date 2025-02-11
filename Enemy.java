import java.awt.Color;

public class Enemy extends Shooter {
    Network network;

    public Enemy(double x, double y, Color colour) {
        super(x, y, colour);

        int[] neuronsPerLayer = {2, 4, 4};
        network = new Network(neuronsPerLayer);
    }

    @Override public void update() {
        // Network inputs:
        // 0: x displacement to player
        // 1: y displacement to player

        // Network outputs:
        // 0: move up
        // 1: move down
        // 2: move left
        // 3: move right

        double[] inputs = new double[2];
        inputs[0] = Constants.shooters.get(0).getX() - getX();
        inputs[1] = Constants.shooters.get(0).getY() - getY();
        double[] outputs = network.feedForward(inputs);

        if (outputs[0] > 0.5) {
            moveUp();
        }
        if (outputs[1] > 0.5) {
            moveDown();
        }
        if (outputs[2] > 0.5) {
            moveLeft();
        }
        if (outputs[3] > 0.5) {
            moveRight();
        }
    }
}
