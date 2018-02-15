package che.per.sidesnapask.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import che.per.sidesnapask.BroadcastModule;
import che.per.sidesnapask.app.AppComponent;
import che.per.sidesnapask.app.BaseApplication;
import che.per.sidesnapask.broadcast.BaseBroadcastReceiver;
import che.per.sidesnapask.broadcast.BroadcastComponent;
import che.per.sidesnapask.view.BroadcastView;

/**
 * Created by Jerry on 2018/1/7.
 */

public class BaseFragment extends Fragment implements BroadcastView {

    @Inject public LocalBroadcastManager mLocalBroadcastManager;
    private BaseBroadcastReceiver mResumeReceiver;

    public BaseFragment() {
        initBroadcastModule();
    }

    private void initBroadcastModule() {
        AppComponent appComponent = BaseApplication.getInstance().getAppComponent();
        BroadcastComponent broadcastComponent = appComponent.plus(new BroadcastModule());
        broadcastComponent.inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    protected void registerResumeBroadcastWithAction(String action) {
        if (mResumeReceiver == null) {
            mResumeReceiver = new BaseBroadcastReceiver(this);
        }
        mLocalBroadcastManager.registerReceiver(mResumeReceiver, new IntentFilter(action));
    }

    protected void registerResumeBroadcast() {

    }

    private void unregisterResumeBroadcast() {
        if (mResumeReceiver != null) {
            mLocalBroadcastManager.unregisterReceiver(mResumeReceiver);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerResumeBroadcast();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterResumeBroadcast();
    }

    @Override
    public void onDestroy() {
        if (mResumeReceiver != null) mResumeReceiver.onDestroy();
        super.onDestroy();
    }
}
