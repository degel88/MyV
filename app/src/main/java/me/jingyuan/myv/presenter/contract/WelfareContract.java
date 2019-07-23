package me.jingyuan.myv.presenter.contract;


import java.util.List;

import me.jingyuan.myv.base.BasePresenter;
import me.jingyuan.myv.base.BaseView;
import me.jingyuan.myv.model.bean.GankItemBean;

/**
 * Description: WelfareContract
 * Creator: degel
 */
public interface WelfareContract {
    interface View extends BaseView {


        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<GankItemBean> list);

        void showMoreContent(List<GankItemBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();

        void loadMore();
    }
}
