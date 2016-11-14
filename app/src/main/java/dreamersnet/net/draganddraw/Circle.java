package dreamersnet.net.draganddraw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by water on 11/14/2016.
 */

public class Circle extends Shapes {
    private PointF mOrigin;
    private PointF mEnd;
    private Paint mPaint;

    public Circle (PointF origin) {
        mOrigin = origin;
        mEnd = origin;
        mPaint = new Paint();
        mPaint.setARGB(0,0,0,0);
    }

    public Paint getPaint() { return mPaint; }

    public void setPaint(Paint p) {
        mPaint = p;
    }

    public PointF getEnd() {
        return mOrigin;
    }

    public void setEnd(PointF endPoint) {
        mEnd = endPoint;
    }

    public void draw(Canvas c) {
        float left = Math.min(mOrigin.x, mEnd.x);
        float right = Math.max(mOrigin.x, mEnd.x);
        float top = Math.min(mOrigin.y, mEnd.y);
        float bottom = Math.max(mOrigin.y, mEnd.y);
        float radius = (float) Math.sqrt(Math.pow((right-left),2.0)+Math.pow((bottom-top),2.0))/2.0f;
        c.drawCircle((right+left)/2.0f,(bottom+top)/2.0f,radius,mPaint);
    }

    public void setOrigin(PointF origin) {
        mOrigin = origin;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
