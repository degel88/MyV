package me.jingyuan.myv.presenter;


import android.util.DisplayMetrics;

import java.util.List;

import javax.inject.Inject;

import me.jingyuan.myv.app.App;
import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.GankItemBean;
import me.jingyuan.myv.model.http.response.GankHttpResponse;
import me.jingyuan.myv.model.net.RetrofitHelper;
import me.jingyuan.myv.presenter.contract.WelfareContract;
import me.jingyuan.myv.utils.RxUtil;
import me.jingyuan.myv.utils.StringUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Description:
 * Creator: degel
 */

public class WelfarePresenter extends RxPresenter<WelfareContract.View> implements WelfareContract.Presenter {
    public static final String PATH = "福利";
    public static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;

    @Inject
    public WelfarePresenter() {
    }

    @Override
    public void onRefresh() {
        currentPage = (int) (1 + Math.random() * (4 - 1 + 1));
        Subscription rxSubscription = RetrofitHelper.getGankApis().getGirlList(PATH, NUM_OF_PAGE, currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        setHeight(gankItemBeen);
                        mView.showContent(gankItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void loadMore() {
        Subscription rxSubscription = RetrofitHelper.getGankApis().getGirlList(PATH, NUM_OF_PAGE, ++currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        setHeight(gankItemBeen);
                        mView.showMoreContent(gankItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载更多数据失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void setHeight(List<GankItemBean> list) {
        DisplayMetrics dm = App.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        /**
         * //宽度为屏幕宽度一半
         */
        int width = dm.widthPixels / 2;
        for (GankItemBean gankItemBean : list) {
            /**
             * //随机的高度
             */
            gankItemBean.setHeight(width * StringUtils.getRandomNumber(3, 6) / 3);
        }
    }

}
