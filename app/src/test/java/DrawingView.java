import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.path;

/**
 * Created by opilane on 15.11.2017.
 */

public class DrawingView extends View {

    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor=0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    public DrawingView (Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        setupDrawing();
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void SetupDrawing(){

        //By instantiating the canvas Paint object
        canvasPaint= new Paint(Paint.DITHER_FLAG);


    }
    @Override
    protected void onSizeChanged(int w, int b, int oldw, int oldb) {
        super.onSizeChanged(w, b, oldw, oldb);
        canvasBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas= new Canvas(canvasBitmap);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX =event.getX();
        float touchY =event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
    public void setColor(String newColor){
        invalidate();
        paintColor= Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
        drawView.setColor(color);

        imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_parent));
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
    }
}
