package me.jingyuan.myv.presenter;


import javax.inject.Inject;

import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.http.response.VideoHttpResponse;
import me.jingyuan.myv.model.net.RetrofitHelper;
import me.jingyuan.myv.presenter.contract.DiscoverContract;
import me.jingyuan.myv.utils.RxUtil;
import me.jingyuan.myv.utils.StringUtils;
import me.jingyuan.myv.utils.SystemUtils;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Description: DiscoverPresenter
 * Creator: degel
 */
public class DiscoverPresenter extends RxPresenter<DiscoverContract.View> implements DiscoverContract.Presenter {
    final String catalogId = "402834815584e463015584e539330016";

    int max = 108;
    int min = 1;

    @Inject
    public DiscoverPresenter() {
    }

    @Override
    public void getData() {
        getNextVideos();
    }

    private void getNextVideos() {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoList(catalogId, getNextPage() + "")
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                               @Override
                               public void call(final VideoRes res) {
                                   if (res != null) {
                                       mView.showContent(res);
                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {

                                   mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                               }
                           }, new Action0() {
                               @Override
                               public void call() {
                                   mView.hidLoading();
                               }
                           }
                );

        addSubscribe(rxSubscription);
    }


    private int getNextPage() {
        int page = mView.getLastPage();
        if (SystemUtils.isNetworkConnected()) {
            page = StringUtils.getRandomNumber(min, max);
            mView.setLastPage(page);
        }
        return page;
    }
}
