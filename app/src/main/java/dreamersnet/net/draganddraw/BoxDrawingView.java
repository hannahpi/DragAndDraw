package dreamersnet.net.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by water on 11/6/2016.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private Shapes mCurrentShape;
    private List<Shapes> mShapes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mTextPaint;
    private Paint mBackgroundPaint;

    //used when creating view in code
    public BoxDrawingView(Context context) {
        this(context,null);
    }

    //used while inflating from xml
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context,attrs);

        //Paint the boxes a nice semitransparent blue
        mBoxPaint = new Paint();
        mBoxPaint.setARGB(34,0,0,255);

        //set text paint
        mTextPaint = new Paint();
        mTextPaint.setARGB(255,0,0,255);
        mTextPaint.setTextSize(30.0f);

        //Background paint
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(),event.getY());
        String action = "";
        if (mCurrentShape == null ) {
            if (mShapes.size()%2 == 0) {
                mCurrentShape = new Box(current);
                mCurrentShape.setPaint(mBoxPaint);

            } else {
                mCurrentShape = new Circle(current);
                mCurrentShape.setPaint(mBoxPaint);
            }
        }

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                //Reset drawing state
                if (mCurrentShape == null) {
                    mCurrentShape = new Box(current);
                    mCurrentShape.setPaint(mBoxPaint);
                }
                mShapes.add(mCurrentShape);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                //update box
                if (mCurrentShape != null) {
                    mCurrentShape.setEnd(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                //release the current box
                mCurrentShape = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentShape = null;
                break;
        }
        Log.i(TAG, action + " at x= " + current.x + ", " + current.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Fill background
        canvas.drawPaint(mBackgroundPaint);

        for (Shapes shape : mShapes) {
            shape.draw(canvas);
        }

        canvas.drawText("" + mShapes.size(), 10.0f,getHeight()-30.0f, mTextPaint);
        if (mShapes.size() < 1) {
            canvas.drawText("You should tap and drag to draw box",30.0f,30.0f,mTextPaint);
        } else if (mShapes.size() < 5) {
            canvas.drawText("Ah you're starting to get into it",100.0f,100.0f,mTextPaint);
        } else if (mShapes.size() < 25) {
            canvas.drawText("Therapeutic isn't it?",200.0f,200.0f,mTextPaint);
        } else if (mShapes.size() < 30) {
            canvas.drawText("Ok... this is getting crazy", 30.0f, 30.0f, mTextPaint);
        } else if (mShapes.size() < 35) {
            canvas.drawText("You've gone box mad", 30.0f, 30.0f,mTextPaint);
        } else if (mShapes.size()< 50) {
            canvas.drawText("You should be tired soon enough", 30.0f, 30.0f, mTextPaint);
        } else {
            canvas.drawText("Seriously?  Still at it?!", 30.0f, 30.0f, mTextPaint);
        }

    }
}
