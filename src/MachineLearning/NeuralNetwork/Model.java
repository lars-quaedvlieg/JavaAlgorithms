package MachineLearning.NeuralNetwork;

import MachineLearning.NeuralNetwork.Layers.Layer;
import MachineLearning.NeuralNetwork.Losses.Loss;
import Mathematics.Functions.ScalarFunction;
import Mathematics.LinearAlgebra.Vector;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final int inputSize;
    private List<Layer> layers;

    private final double learningRate;
    private final Loss lossFunction;

    public Model(int inputSize, double learningRate, Loss lossFunction) {
        assert (inputSize > 0) && (learningRate > 0) && (lossFunction != null);
        this.inputSize = inputSize;
        this.learningRate = learningRate;
        this.lossFunction = lossFunction;
        this.layers = new ArrayList<>();
    }

    public void train(List<Vector> xs, List<Vector> ys, int numIterations) {
        assert xs.size() == ys.size();
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < xs.size(); j++) {
                Vector yPredicted = evaluate(xs.get(j));
                Vector loss = lossFunction.evaluate(yPredicted, ys.get(j));

            }
        }
    }

    public List<Vector> evaluate(List<Vector> input) {
        List<Vector> results = new ArrayList<>();
        for (Vector v: input) {
            results.add(evaluate(v));
        }
        return results;
    }

    public Vector evaluate(Vector input) {
        assert input.getDimensions() == inputSize;
        Vector currentOutput = (Vector) input.clone();
        for (Layer l: layers) {
            currentOutput = l.evaluate(currentOutput);
        }
        return currentOutput;
    }

    public void addLayer(int numNeurons, ScalarFunction activation) {
        int lastOutputSize = (layers.size() > 0) ? layers.get(layers.size()-1).getOutputSize() : inputSize;
        layers.add(new Layer(lastOutputSize, numNeurons, activation));
    }

}
