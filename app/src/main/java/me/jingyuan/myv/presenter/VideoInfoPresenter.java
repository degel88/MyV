package me.jingyuan.myv.presenter;


import org.simple.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import me.jingyuan.myv.base.RxPresenter;
import me.jingyuan.myv.model.bean.Collection;
import me.jingyuan.myv.model.bean.Record;
import me.jingyuan.myv.model.bean.VideoInfo;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.db.RealmHelper;
import me.jingyuan.myv.model.http.response.VideoHttpResponse;
import me.jingyuan.myv.model.net.RetrofitHelper;
import me.jingyuan.myv.presenter.contract.VideoInfoContract;
import me.jingyuan.myv.utils.BeanUtil;
import me.jingyuan.myv.utils.RxUtil;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Description: VideoInfoPresenter
 * Creator: degel
 */
public class VideoInfoPresenter extends RxPresenter<VideoInfoContract.View> implements VideoInfoContract.Presenter {

    public final static String Refresh_Video_Info = "Refresh_Video_Info";
    public final static String Put_DataId = "Put_DataId";
    public static final String Refresh_Collection_List = "Refresh_Collection_List";
    public static final String Refresh_History_List = "Refresh_History_List";
    final int WAIT_TIME = 200;
    VideoRes result;
    String dataId = "";
    String pic = "";

    @Inject
    public VideoInfoPresenter() {
    }

    public void prepareInfo(VideoInfo videoInfo) {
        mView.showContent(BeanUtil.VideoInfo2VideoRes(videoInfo, null));
        this.dataId = videoInfo.dataId;
        this.pic = videoInfo.pic;
        getDetailData(videoInfo.dataId);
        setCollectState();
        putMediaId();
    }


    @Override
    public void getDetailData(String dataId) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoInfo(dataId)
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(final VideoRes res) {
                        if (res != null) {
                            mView.showContent(res);
                            result = res;
                            postData();
                            insertRecord();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hidLoading();
                        mView.showError("数据加载失败");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mView.hidLoading();
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void postData() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post(result, Refresh_Video_Info);
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void putMediaId() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post(dataId, Put_DataId);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void collect() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            RealmHelper.getInstance().deleteCollection(dataId);
            mView.disCollect();
        } else {
            if (result != null) {
                Collection bean = new Collection();
                bean.setId(String.valueOf(dataId));
                bean.setPic(pic);
                bean.setTitle(result.title);
                bean.setAirTime(result.airTime);
                bean.setScore(result.score);
                bean.setTime(System.currentTimeMillis());
                RealmHelper.getInstance().insertCollection(bean);
                mView.collected();
            }
        }
        //刷新收藏列表
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post("", Refresh_Collection_List);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertRecord() {
        if (!RealmHelper.getInstance().queryRecordId(dataId)) {
            if (result != null) {
                Record bean = new Record();
                bean.setId(String.valueOf(dataId));
                bean.setPic(pic);
                bean.setTitle(result.title);
                bean.setTime(System.currentTimeMillis());
                RealmHelper.getInstance().insertRecord(bean, MinePresenter.maxSize);
                //刷新收藏列表
                Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                        .compose(RxUtil.<Long>rxSchedulerHelper())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                EventBus.getDefault().post("", Refresh_History_List);
                            }
                        });
                addSubscribe(rxSubscription);
            }
        }
    }

    private void setCollectState() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            mView.collected();
        } else {
            mView.disCollect();
        }
    }
}
