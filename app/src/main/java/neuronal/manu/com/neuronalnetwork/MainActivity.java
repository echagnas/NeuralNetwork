package neuronal.manu.com.neuronalnetwork;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends ActionBarActivity {

    private Perceptron ptron;
    private Trainer[] training = new Trainer[1000];
    private int width;
    private int height;
    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        graphView = (GraphView)findViewById(R.id.graphview);
        graphView.setSize(width, height);
        graphView.setTrainers(training);
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
        for(int i=0; i<training.length; i++){
            float x = random(-width/2, width/2);
            float y = random(-height/2, height/2);
            int answer = 1;
            if(y<f(x)) answer = -1;
            training[i] = new Trainer(x, y, answer);

            StringBuilder sb = new StringBuilder();
            sb.append("Waiting answer for ");
            sb.append("x="+x+" and y="+y);
            sb.append(" is ");
            sb.append(answer);
            Log.d("RESEAU", sb.toString());
        }
    }

    public static float random(float x, float y){
        float n = y - x;
        return (float)(Math.random() * n - (n/2)); // x < i < y
    }

    private void proceed(){
        for(int j=0; j<training.length; j++) {
            int train = ptron.train(training[j].getInputs(), training[j].getAnswer());

            if(train != training[j].getAnswer()){
                Log.e("RESEAU","Training... => ERROR");
            }else{
                Log.d("RESEAU","Training... => NO ERROR");
            }

            //count = (count + 1) % training.length;
        }

        for(int i=0; i<training.length; i++){
            int guess = ptron.feedForward(training[i].getInputs());
            training[i].setResult(guess);

            StringBuilder sb = new StringBuilder();
            sb.append("Real answer for ");
            sb.append("x="+training[i].getInputs()[0]+" and y="+training[i].getInputs()[1]);
            sb.append(" is ");
            sb.append(guess);
            Log.d("RESEAU", sb.toString());
        }
        graphView.invalidate();
    }
}
