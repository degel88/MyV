package me.jingyuan.myv.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import me.jingyuan.myv.di.scope.ActivityScope;

/**
 * Created by degel
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
