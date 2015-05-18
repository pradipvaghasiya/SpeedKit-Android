package happyfall.speedkit.cells;

import android.view.View;
import android.widget.TextView;

import happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;
import happyfall.speedkit.R;

/**
 * Created by Pradip on 5/13/2015.
 */
public class SPTitleViewHolder extends SPRecyclerAdapter.ViewHolder{
    public static final String CLASS_NAME = "SPTitleViewHolder";

    TextView textView;

    public interface Customizor{
        void customizeTextView(TextView textView, SPTitleViewHolder viewHolder);
    }

    public SPTitleViewHolder(View v, SPRecyclerAdapter.ViewHolderDelegate delegate) {
        super(v,delegate);
        System.out.println("SPTitleViewHolder View Holder Created");
        this.textView = (TextView)v.findViewById(R.id.SPTitleViewHolder_TextView);

        if (this.notifier instanceof Customizor &&
                this.textView != null){
            ((Customizor) this.notifier).customizeTextView(this.textView, this);
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

}
