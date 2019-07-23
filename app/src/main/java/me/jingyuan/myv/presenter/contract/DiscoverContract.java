package me.jingyuan.myv.presenter.contract;


import me.jingyuan.myv.base.BasePresenter;
import me.jingyuan.myv.base.BaseView;
import me.jingyuan.myv.model.bean.VideoRes;

/**
 * Description: RecommendContract
 * Creator: degel
 */
public interface DiscoverContract {

    interface View extends BaseView {

        void showContent(VideoRes videoRes);

        void refreshFaild(String msg);

        void hidLoading();

        int getLastPage();

        void setLastPage(int page);
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
