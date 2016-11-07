package dreamersnet.net.draganddraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by water on 11/6/2016.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_drag_and_draw);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_drag_and_draw);

        if (fragment == null ) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.activity_drag_and_draw,fragment)
                    .commit();
        }
    }
}
