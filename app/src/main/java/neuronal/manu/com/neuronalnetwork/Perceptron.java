package neuronal.manu.com.neuronalnetwork;

import android.util.Log;

/**
 * Created by antares on 19/10/15.
 */
public class Perceptron {

    private float[] weights;
    private float c = 0.01f;

    public Perceptron(int n){
        weights = new float[n];
        for(int i=0; i<weights.length; i++){
            weights[i] = MainActivity.random(-1, 1); // -1 < i < 1
        }
    }

    public int feedForward(float[] inputs){
        float sum = 0;
        for(int i=0; i<weights.length; i++){
            sum += inputs[i] * weights[i];
        }
        return activate(sum);
    }

    private int activate(float sum){
        if(sum > 0) return 1;
        return -1;
    }

    public void train(float[] inputs, int desired){
        Log.d("MANUDEBUG", "training inputs="+inputs+" desired="+desired);
        int guess = feedForward(inputs);
        float error = desired - guess;
        for(int i=0; i<weights.length; i++){
            weights[i] += c * error * inputs[i];
        }
    }

}
