package neuronal.manu.com.neuronalnetwork;

/**
 * Created by manu on 19/10/15.
 */
public class Perceptron {

    //Weights
    private float[] weights;

    //Factor of learning
    private float c = 0.01f;

    public Perceptron(int n){
        weights = new float[n];
        for(int i=0; i<weights.length; i++){
            //Calculate, randomly, the weights
            weights[i] = MainActivity.random(-1, 1); // -1 < i < 1
        }
    }

    //Method of feed-forward
    public int feedForward(float[] inputs){
        float sum = 0;
        for(int i=0; i<weights.length; i++){
            sum += inputs[i] * weights[i];
        }
        return activate(sum);
    }

    //Activation method. Return "1" if the sum if positive.
    private int activate(float sum){
        if(sum > 0) return 1;
        return -1;
    }

    //Training method
    public int train(float[] inputs, int desired){
        int guess = feedForward(inputs);
        float error = desired - guess;
        for(int i=0; i<weights.length; i++){
            //Re-valuation of each weights by the error and the factor of learning.
            weights[i] += c * error * inputs[i];
        }
        return guess;
    }

}
