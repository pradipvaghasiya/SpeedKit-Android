
package ${packageName};

import android.view.View;
import com.bignerdranch.android.multiselector.MultiSelector;
import java.util.List;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

public class ${viewHolderClass} extends SPViewHolder {
    
    public static class ViewModel {
        // Declare View Model Properties here...
    }
    
    public ${viewHolderClass}(View cellView, SPViewHolderListener listener,MultiSelector multiSelector) {
        super(cellView,listener,multiSelector);
        System.out.println("${viewHolderClass} View Holder Created");

        // Create view references using itemView.findViewById here...

        this.customiseViewHolderIfRequired();
    }
    
    @Override
    public void configureCellUsing(Object cellModel) {
    
            // Check if the model is my model.
            if (cellModel instanceof ViewModel){
                    // Configure Cell Here...
            }
    }
    
    public static SPListingCellGroup getCellGroupFromCellModels(List<ViewModel> viewModelList){
        return new SPListingCellGroup(
        R.layout.${layoutName},
        ${viewHolderClass}.class.getName(),
        viewModelList);

    }
}