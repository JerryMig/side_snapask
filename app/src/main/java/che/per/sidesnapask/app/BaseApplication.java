package che.per.sidesnapask.app;

import android.app.Application;
import android.content.Context;

import che.per.sidesnapask.network.NetworkModule;

/**
 * Created by Jerry on 2018/1/3.
 */

public class BaseApplication extends Application {

    private static BaseApplication mApplication;
    private AppComponent mAppComponent;

    public static BaseApplication getInstance() {
        return mApplication;
    }

    private void setInstance(BaseApplication application) {
        if (mApplication == null) {
            mApplication = application;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        createAppComponent();
    }

    private void createAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Context getContext() {
        return mApplication.getApplicationContext();
    }

}
