package che.per;

import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import che.per.sidesnapask.BroadcastModule;
import che.per.sidesnapask.app.AppComponent;
import che.per.sidesnapask.app.BaseApplication;
import che.per.sidesnapask.broadcast.BroadcastComponent;
import che.per.sidesnapask.view.QuestionListModule;
import che.per.sidesnapask.view.fragment.BaseFragment;

/**
 * Created by Jerry on 2018/1/9.
 */

public class TestFragment {

//    @Inject LocalBroadcastManager mLocalBroadcastManager;

    public TestFragment() {
        injectModule();
    }

    private void injectModule() {
//        AppComponent appComponent
//                = ((BaseApplication) getActivity().getApplication()).getAppComponent();
//        BroadcastComponent broadcastComponent = appComponent.plus(new BroadcastModule());
//        broadcastComponent.inject(this);
    }

}
