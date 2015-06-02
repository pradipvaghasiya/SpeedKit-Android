package com.speedui.android.uiautomation.toolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.R;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
abstract public class SPToolBarOnlyFragment extends SPToolBarFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toolbaronly, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.toolbar = (Toolbar) view.findViewById(R.id.toolbar_frag_toolbar);
        this.setupContentFragment();

        if (this.fragmentLifeCycleListener != null){
            this.fragmentLifeCycleListener.onViewCreated(this);
        }
    }


    private void setupContentFragment(){
        Fragment fragment = this.getContentFragment();

        if (fragment != null){
            try {
                android.support.v4.app.FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.toolbaronly_content_frame,fragment)
                        .commit();

            }catch (Exception e){
                throw new RuntimeException("SPToolBarOnlyFragment must be used with AppCompatActivity");
            }
        }
    }

    abstract protected Fragment getContentFragment();
}
