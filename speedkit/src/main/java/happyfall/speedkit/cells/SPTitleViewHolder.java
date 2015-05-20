package happyfall.speedkit.cells;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;

import java.util.List;

import happyfall.speedkit.listing.SPListingCellGroup;
import happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;
import happyfall.speedkit.R;
import happyfall.speedkit.listing.recyclerview.SPViewHolder;
import happyfall.speedkit.listing.recyclerview.SPViewHolderListener;

/**
 * Created by Pradip on 5/13/2015.
 */
public class SPTitleViewHolder extends SPViewHolder {
    public static final String CLASS_NAME = "SPTitleViewHolder";

    TextView textView;

    public interface Customizor{
        void customizeTextView(TextView textView, SPTitleViewHolder viewHolder);
    }

    public SPTitleViewHolder(View v,SPViewHolderListener delegate,MultiSelector multiSelector) {
        super(v,delegate,multiSelector);
        System.out.println("SPTitleViewHolder View Holder Created");
        this.textView = (TextView)v.findViewById(R.id.SPTitleViewHolder_TextView);

        if (this.listener instanceof Customizor &&
                this.textView != null){
            ((Customizor) this.listener).customizeTextView(this.textView, this);
        }
    }

    @Override
    public void configureCellUsing(Object cellModel) {
        if (cellModel instanceof String) {
            if (this.textView != null) {
                this.textView.setText((String) cellModel);
            }
        }
    }

    public static SPListingCellGroup getCellGroupFromCellModels(List<String> modelList){
        return new SPListingCellGroup(
                R.layout.recycler_cell_title,
                SPTitleViewHolder.class.getName(),
                modelList);

    }

}
