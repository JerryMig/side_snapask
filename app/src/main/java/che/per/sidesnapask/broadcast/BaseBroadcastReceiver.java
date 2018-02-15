package che.per.sidesnapask.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import che.per.sidesnapask.view.BroadcastView;

/**
 * Created by Jerry on 2018/1/7.
 */

public class BaseBroadcastReceiver extends BroadcastReceiver {

    private BroadcastView mView;

    public BaseBroadcastReceiver(BroadcastView view) {
        mView = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mView.onReceive(context, intent);
    }

    public void onDestroy() {
        mView = null;
    }

}
