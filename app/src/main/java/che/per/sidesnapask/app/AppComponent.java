package che.per.sidesnapask.app;

import javax.inject.Singleton;

import che.per.sidesnapask.BroadcastModule;
import che.per.sidesnapask.broadcast.BroadcastComponent;
import che.per.sidesnapask.network.NetworkModule;
import che.per.sidesnapask.view.QuestionListComponent;
import che.per.sidesnapask.view.QuestionListModule;
import dagger.Component;

/**
 * Created by Jerry on 2018/1/3.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    QuestionListComponent plus(QuestionListModule module);
    BroadcastComponent plus(BroadcastModule module);
}