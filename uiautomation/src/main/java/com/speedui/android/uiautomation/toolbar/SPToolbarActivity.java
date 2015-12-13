package com.speedui.android.uiautomation.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.speedui.android.uiautomation.R;

public abstract class SPToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sptoolbar);

        SPToolBarFragment fragment = getContentFragment();
        if (fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.sptoolbar_content_frame, fragment).commit();
        }
    }

    protected abstract SPToolBarFragment getContentFragment();
}
