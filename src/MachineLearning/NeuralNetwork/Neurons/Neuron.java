package MachineLearning.NeuralNetwork.Neurons;

import MachineLearning.NeuralNetwork.Activations.ActivationFunctions;
import MachineLearning.NeuralNetwork.Activations.Identity;

public class Neuron {

    public ActivationFunctions activation;
    public double value = 0;

    public Neuron() {
        this.activation = new Identity();
    }

    public Neuron(ActivationFunctions activation) {
        this.activation = activation;
    }

    /*public static void main(String[] args) {
        Neuron n1 = new Neuron(new MachineLearning.NeuralNetwork.Activations.PReLu(0.01));
        System.out.println(n1.activation.evaluate(10));
    }*/

}