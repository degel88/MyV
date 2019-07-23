package me.jingyuan.myv.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jingyuan.myv.app.App;
import me.jingyuan.myv.model.DataManager;
import me.jingyuan.myv.model.db.DBHelper;
import me.jingyuan.myv.model.db.RealmHelper;
import me.jingyuan.myv.model.http.HttpHelper;
import me.jingyuan.myv.model.http.RetrofitHelper;

/**
 * 描述：me.jingyuan.myv.di.module
 * 作者：degel on 2019-07-22 16:25
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper) {
        return new DataManager(httpHelper, DBHelper);
    }
}
