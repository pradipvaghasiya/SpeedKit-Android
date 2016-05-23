package com.happyfall.android.swiftui.reuse.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.happyfall.android.swiftui.reuse.fragment.toolbar.ToolBarFragment;
import com.happyfall.android.swiftui.R;

public abstract class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        ToolBarFragment fragment = getContentFragment();
        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.toolbar_content_frame, fragment).commit();
        }
    }

    protected abstract ToolBarFragment getContentFragment();
}
