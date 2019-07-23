package me.jingyuan.myv.presenter;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.Record;
import me.jingyuan.myv.model.bean.VideoType;
import me.jingyuan.myv.model.db.RealmHelper;
import me.jingyuan.myv.presenter.contract.MineContract;

/**
 * Description: CollectionPresenter
 * Creator: degel
 */
public class MinePresenter extends RxPresenter<MineContract.View> implements MineContract.Presenter {
    public static final int maxSize = 30;

    @Inject
    public MinePresenter() {
    }

    @Override
    public void getHistoryData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        int maxlinth = records.size() <= 3 ? records.size() : 3;
        for (int i = 0; i < maxlinth; i++) {
            Record record = records.get(i);
            videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllHistory() {
        RealmHelper.getInstance().deleteAllRecord();
    }

}
