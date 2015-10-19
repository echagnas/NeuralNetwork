package neuronal.manu.com.neuronalnetwork;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by antares on 19/10/15.
 */
public class GraphView extends View {

    private Trainer[] training;

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTrainers(Trainer[] training){
        this.training = training;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(training != null){
            for(Trainer trainer : training){
                float[] inputs = trainer.getInputs();
                float x = inputs[0];
                float y = inputs[1];
                Paint paint = new Paint();
                if(trainer.getResult() == 1) {
                    paint.setColor(getResources().getColor(R.color.red));
                }else{
                    paint.setColor(getResources().getColor(R.color.blue));
                }
                //canvas.drawPoint(x, y, paint);
                canvas.drawText("X", x, y, paint);
            }
        }
    }
}
