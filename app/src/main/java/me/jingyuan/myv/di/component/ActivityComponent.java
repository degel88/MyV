package me.jingyuan.myv.di.component;

import android.app.Activity;

import dagger.Component;
import me.jingyuan.myv.di.module.ActivityModule;
import me.jingyuan.myv.di.scope.ActivityScope;
import me.jingyuan.myv.ui.activitys.CollectionActivity;
import me.jingyuan.myv.ui.activitys.HistoryActivity;
import me.jingyuan.myv.ui.activitys.SearchActivity;
import me.jingyuan.myv.ui.activitys.VideoInfoActivity;
import me.jingyuan.myv.ui.activitys.VideoListActivity;
import me.jingyuan.myv.ui.activitys.WelcomeActivity;
import me.jingyuan.myv.ui.activitys.WelfareActivity;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(VideoInfoActivity videoInfoActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(CollectionActivity collectionActivity);

    void inject(HistoryActivity historyActivity);

    void inject(SearchActivity searchActivity);

    void inject(VideoListActivity videoListActivity);

    void inject(WelfareActivity welfareActivity);

}
