package me.jingyuan.myv.base;

import android.os.Bundle;

import javax.inject.Inject;

import me.jingyuan.myv.app.App;
import me.jingyuan.myv.di.component.ActivityComponent;
import me.jingyuan.myv.di.component.DaggerActivityComponent;
import me.jingyuan.myv.di.module.ActivityModule;

/**
 * Description: MVP Activity基类
 * Creator: degel
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void initInject();

}
