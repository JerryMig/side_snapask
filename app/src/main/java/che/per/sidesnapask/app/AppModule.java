package che.per.sidesnapask.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2018/1/3.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Application application) {
        mContext = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return mContext.getResources();
    }

}
