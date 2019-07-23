package me.jingyuan.myv.presenter;


import javax.inject.Inject;

import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.exception.ApiException;
import me.jingyuan.myv.model.net.HttpMethods;
import me.jingyuan.myv.model.net.MyObserver;
import me.jingyuan.myv.presenter.contract.ClassificationContract;

/**
 * Description: ClassificationPresenter
 * Creator: degel
 */
public class ClassificationPresenter extends RxPresenter<ClassificationContract.View> implements ClassificationContract.Presenter {
    int page = 0;

    @Inject
    public ClassificationPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        HttpMethods.getInstance().queryClassification()
                .subscribe(new MyObserver<VideoRes>() {
                    @Override
                    protected void onError(ApiException ex) {
                        mView.refreshFaild(ex.getDisplayMessage());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onNext(VideoRes res) {
                        if (res != null) {
                            mView.showContent(res);
                        }
                    }
                });
    }
}
