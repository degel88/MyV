package me.jingyuan.myv.presenter.contract;


import java.util.List;

import me.jingyuan.myv.base.BasePresenter;
import me.jingyuan.myv.base.BaseView;
import me.jingyuan.myv.model.bean.VideoType;

/**
 * Description: CommentContract
 * Creator: degel
 */
public interface CommentContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void showContent(List<VideoType> list);

        void showMoreContent(List<VideoType> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh();

        void loadMore();

        void setMediaId(String id);

    }
}
