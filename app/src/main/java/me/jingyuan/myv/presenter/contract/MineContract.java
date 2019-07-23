package me.jingyuan.myv.presenter.contract;


import java.util.List;

import me.jingyuan.myv.base.BasePresenter;
import me.jingyuan.myv.base.BaseView;
import me.jingyuan.myv.model.bean.VideoType;

/**
 * Description: CollectionContract
 * Creator: degel
 */
public interface MineContract {

    interface View extends BaseView {

        void showContent(List<VideoType> list);

    }

    interface Presenter extends BasePresenter<View> {
        void getHistoryData();

        void delAllHistory();
    }
}
