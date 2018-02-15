package che.per.sidesnapask.view.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Jerry on 2018/1/8.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setDate(T data);

}
