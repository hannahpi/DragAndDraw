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

import static android.R.attr.action;

/**
 * Created by water on 11/6/2016.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();
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

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                //Reset drawing state
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                //update box
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                //release the current box
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }
        Log.i(TAG, action + " at x= " + current.x + ", " + current.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Fill background
        canvas.drawPaint(mBackgroundPaint);

        for (Box box: mBoxen) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left,top,right,bottom, mBoxPaint);
        }
        canvas.drawText("" + mBoxen.size(), 10.0f,getHeight()-30.0f, mTextPaint);
        if (mBoxen.size() < 1) {
            canvas.drawText("You should tap and drag to draw box",30.0f,30.0f,mTextPaint);
        } else if (mBoxen.size() < 5) {
            canvas.drawText("Ah you're starting to get into it",100.0f,100.0f,mTextPaint);
        } else if (mBoxen.size() < 25) {
            canvas.drawText("Therapeutic isn't it?",200.0f,200.0f,mTextPaint);
        } else if (mBoxen.size() < 30) {
            canvas.drawText("Ok... this is getting crazy", 30.0f, 30.0f, mTextPaint);
        } else if (mBoxen.size() < 35) {
            canvas.drawText("You've gone box mad", 30.0f, 30.0f,mTextPaint);
        } else if (mBoxen.size()< 50) {
            canvas.drawText("You should be tired soon enough", 30.0f, 30.0f, mTextPaint);
        } else {
            canvas.drawText("Seriously?  Still at it?!", 30.0f, 30.0f, mTextPaint);
        }

    }
}
