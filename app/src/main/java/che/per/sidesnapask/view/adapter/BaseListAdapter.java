package che.per.sidesnapask.view.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Jerry on 2018/1/8.
 */

public abstract class BaseListAdapter<V extends RecyclerView.ViewHolder, D extends List>
        extends RecyclerView.Adapter<V> {
    public abstract void setData(D data);
    public abstract void setDataWithUtil(D data);
}
