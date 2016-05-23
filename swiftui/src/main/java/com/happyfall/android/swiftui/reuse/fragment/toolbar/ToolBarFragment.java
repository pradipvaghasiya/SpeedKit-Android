package com.happyfall.android.swiftui.reuse.fragment.toolbar;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
abstract public class ToolBarFragment extends Fragment {

    public interface ToolBarFragmentListner {
        void onViewCreated(ToolBarFragment fragment);
    }

    public ToolBarFragmentListner mToolBarFragmentListner;

    public Toolbar toolbar;

    public ToolBarFragment() {
        // Required empty public constructor
    }

}
