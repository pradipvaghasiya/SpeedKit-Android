package com.happyfall.android.swiftui.reuse.fragment.toolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.happyfall.android.swiftui.R;


/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public class LightToolBarFragment extends ToolBarFragment {
    public LightToolBarFragment(){

    }

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
        this.toolbar.setTitle(getActivity().getTitle());
        this.setupContentFragment();

        if (this.mToolBarFragmentListner != null){
            this.mToolBarFragmentListner.onViewCreated(this);
        }
    }


    private void setupContentFragment(){
        Fragment fragment = this.getContentFragment();

        if (fragment != null){
            try {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.toolbaronly_content_frame,fragment)
                        .commit();

            }catch (Exception e){
                throw new RuntimeException("LightToolBarFragment must be used with AppCompatActivity");
            }
        }
    }

    protected Fragment getContentFragment() {
        ToolbarFMDatasource datasource;
        if (getActivity() instanceof ToolbarFMDatasource) {
            datasource= (ToolbarFMDatasource) getActivity();
        } else {
            throw new RuntimeException("ToolbarFM Datasource not found");
        }
        return datasource.getContentFragmentOfToolBarFragment();
    }

    public interface ToolbarFMDatasource{
        Fragment getContentFragmentOfToolBarFragment();
    }
}
