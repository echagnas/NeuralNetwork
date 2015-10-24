package neuronal.manu.com.neuronalnetwork;

/**
 * Created by manu on 19/10/15.
 */
public class Trainer {

    private float[] inputs;
    private int answer;
    private int result;

    public Trainer(float x, float y, int a){
        inputs = new float[3];
        inputs[0] = x; //x
        inputs[1] = y; //y
        inputs[2] = 1; //bias
        answer = a; //the answer
    }

    public float[] getInputs() {
        return inputs;
    }

    public int getAnswer() {
        return answer;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
