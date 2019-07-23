package me.jingyuan.myv.presenter;


import javax.inject.Inject;

import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.http.response.VideoHttpResponse;
import me.jingyuan.myv.model.net.RetrofitHelper;
import me.jingyuan.myv.presenter.contract.RecommendContract;
import me.jingyuan.myv.utils.RxUtil;
import me.jingyuan.myv.utils.StringUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: RecommendPresenter
 * Creator: degel
 */
public class RecommendPresenter extends RxPresenter<RecommendContract.View> implements RecommendContract.Presenter {

    int page = 0;

    @Inject
    public RecommendPresenter() {}

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getHomePage()
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
                });
        addSubscribe(rxSubscription);
    }

}
