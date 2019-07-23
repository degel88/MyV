package me.jingyuan.myv.presenter.contract;


import me.jingyuan.myv.base.BasePresenter;
import me.jingyuan.myv.base.BaseView;
import me.jingyuan.myv.model.bean.VideoRes;

public interface VideoInfoContract {

    interface View extends BaseView {

        void showContent(VideoRes videoRes);

        void hidLoading();

        void collected();

        void disCollect();
    }

    interface Presenter extends BasePresenter<View> {
        void getDetailData(String dataId);

        void collect();

        void insertRecord();

    }
}
