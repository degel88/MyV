package me.jingyuan.myv.presenter;


import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.Collection;
import me.jingyuan.myv.model.bean.Record;
import me.jingyuan.myv.model.bean.VideoType;
import me.jingyuan.myv.model.db.RealmHelper;
import me.jingyuan.myv.presenter.contract.CollectionContract;

/**
 * Description: CollectionPresenter
 * Creator: degel
 */
public class CollectionPresenter extends RxPresenter<CollectionContract.View> implements CollectionContract.Presenter {
    /**
     * //收藏:0; 历史:1:
     */
    int type = 0;

    @Inject
    public CollectionPresenter() {
    }

    @Override
    public void getData(int type) {
        this.type = type;
        if (type == 0) {
            getCollectData();
        } else {
            getRecordData();
        }
    }

    @Override
    public void getCollectData() {
        List<Collection> collections = RealmHelper.getInstance().getCollectionList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        for (Collection collection : collections) {
            videoType = new VideoType();
            videoType.title = collection.title;
            videoType.pic = collection.pic;
            videoType.dataId = collection.getId();
            videoType.score = collection.getScore();
            videoType.airTime = collection.getAirTime();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllDatas() {
        if (type == 0) {
            RealmHelper.getInstance().deleteAllCollection();
        } else {
            RealmHelper.getInstance().deleteAllRecord();
            EventBus.getDefault().post("", VideoInfoPresenter.Refresh_History_List);
        }
    }

    @Override
    public void getRecordData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        for (Record record : records) {
            videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public int getType() {
        return type;
    }
}
