public class Neuron {
    private double[] weights;
    private double bias;
    private double output;

    public Neuron(int numInputs) {
        weights = new double[numInputs];
        for (int i = 0; i < numInputs; i++) {
            weights[i] = Math.random() * 2 - 1;
        }
        bias = Math.random() * 2 - 1;
    }

    public double feedForward(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        sum += bias;
        output = sigmoid(sum);
        return output;
    }

    public void mutate() {
        for (int i = 0; i < weights.length; i++) {
            if (Math.random() < 0.1) {
                weights[i] += Math.random() * 2 - 1;
            }
        }
        if (Math.random() < 0.1) {
            bias += Math.random() * 2 - 1;
        }
    }

    public Neuron copy() {
        Neuron copy = new Neuron(weights.length);
        for (int i = 0; i < weights.length; i++) {
            copy.weights[i] = weights[i];
        }
        copy.bias = bias;
        return copy;
    }

    public double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public double[] getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }

    public double getOutput() {
        return output;
    }
}
