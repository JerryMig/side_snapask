package che.per.sidesnapask.broadcast;

import javax.inject.Singleton;

import che.per.sidesnapask.BroadcastModule;
import che.per.sidesnapask.view.fragment.BaseFragment;
import dagger.Subcomponent;

/**
 * Created by Jerry on 2018/1/7.
 */

@Subcomponent(modules = BroadcastModule.class)
public interface BroadcastComponent {
    void inject(BaseFragment fragment);
}
