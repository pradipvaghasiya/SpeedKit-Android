package com.speedui.android.uiautomation.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * A simple {@link Fragment} subclass.
 */
abstract public class SPToolBarFragment extends Fragment {

    public interface  SPFragmentLifeCycleListener{
//        void onAttach(SPToolBarFragment fragment);
        void onViewCreated(SPToolBarFragment fragment);

    }
    public SPFragmentLifeCycleListener fragmentLifeCycleListener;

    public Toolbar toolbar;

    public SPToolBarFragment() {
        // Required empty public constructor
    }

}
