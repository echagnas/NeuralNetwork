package neuronal.manu.com.neuronalnetwork;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends ActionBarActivity {

    //The neural network, a perceptron
    private Perceptron ptron;

    //Creation of 1000 data
    private Trainer[] training = new Trainer[1000];

    //Size of the plan where points are
    private int width, height;

    //The graph that show the results
    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Initialisation of variables
        setup();

        //Configuration of the grahview that show the results
        graphView = (GraphView)findViewById(R.id.graphview);
        graphView.setSize(width, height);
        //Give the points to the graph
        graphView.setTrainers(training);

        //Learn and proceed the results
        proceed();
    }

    //Method that return the waiting results.
    // This is the function of the line.
    private float f(float x){
        return 2*x+1;
    }

    //Initialisation of the size of the plan
    private void size(int width, int height){
        this.width = width;
        this.height = height;
    }

    //Initilisations
    private void setup(){
        //Initialize the plan where points are
        size(640, 360);

        //Create perceptron with 3 inputs
        ptron = new Perceptron(3);
        //For each data, calculate with random the coordinates.
        for(int i=0; i<training.length; i++){
            float x = random(-width/2, width/2);
            float y = random(-height/2, height/2);

            //Calculate the real result.
            int answer = 1;
            if(y<f(x)) answer = -1;

            //Give the random coordinates and the result to the input.
            training[i] = new Trainer(x, y, answer);

            //Show log
            StringBuilder sb = new StringBuilder();
            sb.append("Waiting answer for ");
            sb.append("x="+x+" and y="+y);
            sb.append(" is ");
            sb.append(answer);
            Log.d("RESEAU", sb.toString());
        }
    }

    //Give a random float between 2 float.
    public static float random(float x, float y){
        float n = y - x;
        return (float)(Math.random() * n - (n/2)); // x < i < y
    }

    //Train and proceed the results.
    private void proceed(){
        //Training...
        for(int j=0; j<training.length; j++) {
            int train = ptron.train(training[j].getInputs(), training[j].getAnswer());

            //Show log
            if(train != training[j].getAnswer()){
                Log.e("RESEAU","Training... => ERROR");
            }else{
                Log.d("RESEAU","Training... => NO ERROR");
            }
        }

        //Proceed...
        for(int i=0; i<training.length; i++){
            //Proceed by feed-forward method.
            int guess = ptron.feedForward(training[i].getInputs());
            training[i].setResult(guess);

            //Show log
            StringBuilder sb = new StringBuilder();
            sb.append("Real answer for ");
            sb.append("x="+training[i].getInputs()[0]+" and y="+training[i].getInputs()[1]);
            sb.append(" is ");
            sb.append(guess);
            Log.d("RESEAU", sb.toString());
        }

        //Draw the points
        graphView.invalidate();
    }
}
