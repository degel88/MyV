package me.jingyuan.myv.presenter.contract;


import java.util.List;

import me.jingyuan.myv.base.BasePresenter;
import me.jingyuan.myv.base.BaseView;
import me.jingyuan.myv.model.bean.VideoType;

/**
 * Description: VideoListContract
 * Creator: degel
 */
public interface VideoListContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<VideoType> list);

        void showMoreContent(List<VideoType> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh(String catalogId);

        void loadMore();

    }
}
