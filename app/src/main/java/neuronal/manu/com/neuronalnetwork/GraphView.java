package neuronal.manu.com.neuronalnetwork;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by manu on 19/10/15.
 */
public class GraphView extends View {

    private Trainer[] training;
    private int width, height;

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

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int widthCanvas = canvas.getWidth();
        int heightCanvas = canvas.getHeight();

        float w = widthCanvas / width;
        float h = heightCanvas / height;

        float refSize = w > h ? w : h;

        int dpSize =  2;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dm);

        //Draw Axis
        Paint axisPaint = new Paint();
        axisPaint.setStrokeWidth(strokeWidth);
        axisPaint.setColor(getResources().getColor(R.color.green));
        canvas.drawLine(width/2, (-height/2)*refSize, width/2, (height/2)*refSize, axisPaint);
        canvas.drawLine((-width/2)*refSize, height/2, (width/2)*refSize, height/2, axisPaint);

        //Draw line
        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(strokeWidth);
        linePaint.setColor(getResources().getColor(R.color.line));
        canvas.drawLine((-320/refSize)+width/2, -(-639/refSize)+height/2, (320/refSize)+width/2, -(641/refSize)+height/2, linePaint);

        //Draw points
        if(training != null){
            for(Trainer trainer : training){
                float[] inputs = trainer.getInputs();
                float x = inputs[0]/refSize;
                float y = -(inputs[1]/refSize);
                Paint paint = new Paint();
                if(trainer.getResult() == 1) {
                    //Red if the neural network said the point is above the line
                    paint.setColor(getResources().getColor(R.color.red));
                }else{
                    //Blue if the neural network said the point is below the line
                    paint.setColor(getResources().getColor(R.color.blue));
                }
                canvas.drawText("X", x+width/2, y+height/2, paint);
            }
        }
    }
}
