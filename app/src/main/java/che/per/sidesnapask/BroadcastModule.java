package che.per.sidesnapask;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2018/1/7.
 */

@Module
public class BroadcastModule {

    @Provides
    LocalBroadcastManager provideLocalBroadcastManager(Context context) {
        return LocalBroadcastManager.getInstance(context);
    }

}
