package me.jingyuan.myv.ui.activitys;


import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.jingyuan.myv.R;
import me.jingyuan.myv.base.SwipeBackActivity;
import me.jingyuan.myv.model.bean.VideoInfo;
import me.jingyuan.myv.model.bean.VideoType;
import me.jingyuan.myv.presenter.CollectionPresenter;
import me.jingyuan.myv.presenter.VideoInfoPresenter;
import me.jingyuan.myv.presenter.contract.CollectionContract;
import me.jingyuan.myv.ui.adapter.VideoListAdapter;
import me.jingyuan.myv.utils.BeanUtil;
import me.jingyuan.myv.utils.EventUtil;
import me.jingyuan.myv.utils.ScreenUtil;
import me.jingyuan.myv.widget.theme.ColorTextView;

/**
 * Description: 播放历史
 * Creator: degel
 */
public class HistoryActivity extends SwipeBackActivity<CollectionPresenter> implements CollectionContract.View {

    @BindView(R.id.rl_collect_clear)
    RelativeLayout rlCollectClear;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    VideoListAdapter mAdapter;
    VideoInfo videoInfo;

    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        setTitle();
        rlCollectClear.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapterWithProgress(mAdapter = new VideoListAdapter(mContext));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);

        mPresenter.getData(1);
    }

    @Override
    protected void initEvent() {
        mAdapter.setOnItemClickListener(position -> {
            videoInfo = BeanUtil.VideoType2VideoInfo(mAdapter.getItem(position), videoInfo);
            VideoInfoActivity.start(mContext, videoInfo);
        });
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }


    @Override
    public void showContent(List<VideoType> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
    }

    @OnClick({R.id.rl_back, R.id.rl_collect_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                if (mContext instanceof CollectionActivity) {
                    finish();
                } else if (mContext instanceof HistoryActivity) {
                    finish();
                }
                break;
            case R.id.rl_collect_clear:
                mAdapter.clear();
                mPresenter.delAllDatas();
                break;
            default:
                break;
        }
    }

    private void setTitle() {
        if (mContext instanceof CollectionActivity) {
            titleName.setText("收藏");
        } else if (mContext instanceof HistoryActivity) {
            titleName.setText("历史");
        }
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_Collection_List)
    public void setData(String tag) {
        mPresenter.getCollectData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
