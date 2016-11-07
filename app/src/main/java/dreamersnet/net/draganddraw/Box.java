package dreamersnet.net.draganddraw;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by water on 11/6/2016.
 */

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box (PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
