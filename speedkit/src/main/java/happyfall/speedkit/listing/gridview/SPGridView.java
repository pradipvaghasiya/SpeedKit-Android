package happyfall.speedkit.listing.gridview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.GridView;

import happyfall.speedkit.listing.SPListingData;

/**
 * Created by Pradip on 5/12/2015.
 */
public class SPGridView extends GridView {
    private SPListingData listingData;
    public SPGridView(Context context) {
        super(context);
    }

    public SPGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SPGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SPGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setListingData(SPListingData listingData) {
        if (this.listingData != listingData){
            this.listingData = listingData;

            //TODO Check Whether This gets call even when same instance gets changed.
            this.setAdapter(new SPGridAdapter(this.listingData));
        }
    }
}
