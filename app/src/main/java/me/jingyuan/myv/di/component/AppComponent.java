package me.jingyuan.myv.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.jingyuan.myv.app.App;
import me.jingyuan.myv.di.module.AppModule;
import me.jingyuan.myv.di.module.HttpModule;
import me.jingyuan.myv.model.DataManager;
import me.jingyuan.myv.model.db.RealmHelper;
import me.jingyuan.myv.model.http.RetrofitHelper;

/**
 * desc：me.jingyuan.myv.di.component
 *
 * @author：degel on 2019-07-22 16:44
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    /**
     * 提供App的Context
     *
     * @return
     */
    App getContext();

    /**
     * 数据中心
     *
     * @return
     */
    DataManager getDataManager();

    /**
     * 提供http的帮助类
     *
     * @return
     */
    RetrofitHelper retrofitHelper();

    /**
     * 提供数据库帮助类
     *
     * @return
     */
    RealmHelper realmHelper();
}
