package dreamersnet.net.draganddraw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by water on 11/14/2016.
 */

public abstract class Shapes {
    protected abstract void draw(Canvas c);
    abstract PointF getOrigin();
    abstract PointF getEnd();
    abstract void setOrigin(PointF point);
    abstract void setEnd(PointF point);
    abstract void setPaint(Paint paint);
    abstract Paint getPaint();

}
