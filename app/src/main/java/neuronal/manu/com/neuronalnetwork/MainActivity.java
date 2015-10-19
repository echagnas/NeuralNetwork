package neuronal.manu.com.neuronalnetwork;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends ActionBarActivity {

    private Perceptron ptron;
    private Trainer[] training = new Trainer[100];
    private int count = 0;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        proceed();
    }

    private float f(float x){
        return 2*x+1;
    }

    private void size(int width, int height){
        this.width = width;
        this.height = height;
    }

    private void setup(){
        size(640, 360);
        ptron = new Perceptron(3);
        StringBuilder sb = new StringBuilder();
        sb.append("Answers=");
        for(int i=0; i<training.length; i++){
            float x = random(-width/2, width/2);
            float y = random(-height/2, height/2);
            int answer = 1;
            if(y<f(x)) answer = -1;
            training[i] = new Trainer(x, y, answer);
            if(sb.length() > 0){
                sb.append("; ");
            }
            sb.append(answer);
        }
        Log.d("NEURON", sb.toString());
    }

    public static float random(float x, float y){
        float n = y - x;
        return (float)(Math.random() * n - (n/2)); // x < i < y
    }

    private void proceed(){
        ptron.train(training[count].getInputs(), training[count].getAnswer());
        count = (count + 1) % training.length;
        StringBuilder sb = new StringBuilder();
        sb.append("Responses=");
        for(int i=0; i<count; i++){
            int guess = ptron.feedForward(training[i].getInputs());
            if(sb.length() > 0){
                sb.append("; ");
            }
            sb.append(guess);
        }
        Log.d("NEURON", sb.toString());
    }
}
