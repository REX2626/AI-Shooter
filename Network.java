public class Network {
    private Neuron[][] layers;

    public Network(int[] neuronsPerLayer) {
        layers = new Neuron[neuronsPerLayer.length][];
        for (int i = 0; i < neuronsPerLayer.length; i++) {
            layers[i] = new Neuron[neuronsPerLayer[i]];
            for (int j = 0; j < neuronsPerLayer[i]; j++) {
                if (i == 0) {
                    layers[i][j] = new Neuron(1);
                } else {
                    layers[i][j] = new Neuron(neuronsPerLayer[i-1]);
                }
            }
        }
    }

    public double[] feedForward(double[] inputs) {
        double[] outputs = new double[layers[0].length];
        for (int i = 0; i < layers[0].length; i++) {
            double[] input = new double[1];
            input[0] = inputs[i];
            outputs[i] = layers[0][i].feedForward(input);
        }
        for (int i = 1; i < layers.length; i++) {
            double[] newInputs = outputs;
            outputs = new double[layers[i].length];
            for (int j = 0; j < layers[i].length; j++) {
                outputs[j] = layers[i][j].feedForward(newInputs);
            }
        }
        return outputs;
    }

    public void mutate() {
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].length; j++) {
                layers[i][j].mutate();
            }
        }
    }

    public Network copy() {
        int numLayers = layers.length;
        int[] neuronsPerLayer = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            neuronsPerLayer[i] = layers[i].length;
        }
        Network copy = new Network(neuronsPerLayer);
        for (int i = 0; i < numLayers; i++) {
            for (int j = 0; j < layers[i].length; j++) {
                copy.layers[i][j] = layers[i][j].copy();
            }
        }
        return copy;
    }
}
