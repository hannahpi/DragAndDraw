package dreamersnet.net.draganddraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public class DragAndDrawActivity extends SingleFragmentActivity {

    @Override
    public DragAndDrawFragment createFragment() {
        return DragAndDrawFragment.newInstance();
    }

}
