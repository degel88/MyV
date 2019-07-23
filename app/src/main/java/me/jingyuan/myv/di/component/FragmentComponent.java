package me.jingyuan.myv.di.component;

import android.app.Activity;

import dagger.Component;
import me.jingyuan.myv.di.module.FragmentModule;
import me.jingyuan.myv.di.scope.FragmentScope;
import me.jingyuan.myv.ui.fragments.ClassificationFragment;
import me.jingyuan.myv.ui.fragments.CommentFragment;
import me.jingyuan.myv.ui.fragments.DiscoverFragment;
import me.jingyuan.myv.ui.fragments.MineFragment;
import me.jingyuan.myv.ui.fragments.RecommendFragment;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DiscoverFragment dailyFragment);

    void inject(ClassificationFragment dailyFragment);

    void inject(RecommendFragment recommendFragment);

    void inject(MineFragment mineFragment);

    void inject(CommentFragment commentFragment);

//    void inject(VideoIntroFragment videoIntroFragment);

}
