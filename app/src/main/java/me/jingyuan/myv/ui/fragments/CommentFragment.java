package me.jingyuan.myv.ui.fragments;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import me.jingyuan.myv.R;
import me.jingyuan.myv.base.BaseMvpFragment;
import me.jingyuan.myv.model.bean.VideoType;
import me.jingyuan.myv.presenter.CommentPresenter;
import me.jingyuan.myv.presenter.VideoInfoPresenter;
import me.jingyuan.myv.presenter.contract.CommentContract;
import me.jingyuan.myv.ui.adapter.CommentAdapter;
import me.jingyuan.myv.utils.EventUtil;
import me.jingyuan.myv.utils.ScreenUtil;

/**
 * Description: 详情--评论
 * Creator: degel
 */
public class CommentFragment extends BaseMvpFragment<CommentPresenter> implements CommentContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    TextView tv_empty;

    CommentAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        recyclerView.setAdapterWithProgress(adapter = new CommentAdapter(mContext));
        recyclerView.setErrorView(R.layout.view_error);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        tv_empty = (TextView) recyclerView.getEmptyView();
        tv_empty.setText("暂无评论");
    }

    @Override
    protected void initEvent() {
        recyclerView.setRefreshListener(this);
        adapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });
        recyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                onRefresh();
            }
        });
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showError(msg);
        }
        recyclerView.showError();
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.onRefresh();
    }

    public void clearFooter() {
        adapter.setMore(new View(mContext), this);
        adapter.setError(new View(mContext));
        adapter.setNoMore(new View(mContext));
    }

    @Override
    public void showContent(List<VideoType> list) {
        adapter.clear();
        if (list != null && list.size() < 30) {
            clearFooter();
        }
        adapter.addAll(list);
    }

    @Override
    public void showMoreContent(List<VideoType> list) {
        adapter.addAll(list);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Subscriber(tag = VideoInfoPresenter.Put_DataId)
    public void setData(String dataId) {
        mPresenter.setMediaId(dataId);
        mPresenter.onRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
