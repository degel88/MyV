package me.jingyuan.myv.ui.activitys;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.jingyuan.myv.R;
import me.jingyuan.myv.base.SwipeBackActivity;
import me.jingyuan.myv.component.ImageLoader;
import me.jingyuan.myv.model.bean.VideoInfo;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.presenter.VideoInfoPresenter;
import me.jingyuan.myv.presenter.contract.VideoInfoContract;
import me.jingyuan.myv.ui.fragments.CommentFragment;
import me.jingyuan.myv.ui.fragments.VideoIntroFragment;
import me.jingyuan.myv.utils.EventUtil;
import me.jingyuan.myv.widget.LVGhost;
import me.jingyuan.myv.widget.SwipeViewPager;
import me.jingyuan.myv.widget.theme.ColorTextView;

/**
 * Description: 影片详情
 * @author : degel
 */
public class VideoInfoActivity extends SwipeBackActivity<VideoInfoPresenter> implements VideoInfoContract.View {

    VideoInfo videoInfo;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.title_name)
    ColorTextView mTitleName;
    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;
    @BindView(R.id.viewpager)
    SwipeViewPager mViewpager;
    @BindView(R.id.circle_loading)
    LVGhost mLoading;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;

    VideoRes videoRes;
    private Animation animation;

    @Override
    protected void initView() {
        animation = AnimationUtils.loadAnimation(mContext, R.anim.view_hand);
        rlCollect.setVisibility(View.VISIBLE);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(mContext)
                .add(R.string.video_intro, VideoIntroFragment.class)
                .add(R.string.video_comment, CommentFragment.class)
                .create());
        mViewpager.setAdapter(adapter);
        mViewpagertab.setViewPager(mViewpager);
        videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoplayer.backButton.setVisibility(View.GONE);
        videoplayer.titleTextView.setVisibility(View.GONE);
        videoplayer.tinyBackImageView.setVisibility(View.GONE);

        mPresenter.prepareInfo(videoInfo);
    }

    @Override
    protected void getIntentData() {
        videoInfo = (VideoInfo) getIntent().getSerializableExtra("videoInfo");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_video_info;
    }

    @OnClick(R.id.rl_back)
    public void back() {
        finish();
    }

    @Override
    public void hidLoading() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void collected() {
        ivCollect.setBackgroundResource(R.mipmap.collection_select);
    }

    @Override
    public void disCollect() {
        ivCollect.setBackgroundResource(R.mipmap.collection);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(VideoRes videoRes) {
        this.videoRes = videoRes;
        mTitleName.setText(videoRes.title);
        if (!TextUtils.isEmpty(videoRes.pic)) {
            ImageLoader.load(mContext, videoRes.pic, videoplayer.thumbImageView);
        }
        if (!TextUtils.isEmpty(videoRes.getVideoUrl())) {
            videoplayer.setUp(videoRes.getVideoUrl()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoRes.title);
            videoplayer.onClick(videoplayer.thumbImageView);
        }
    }

    @OnClick(R.id.rl_collect)
    public void onClick() {
        if (videoRes != null) {
            ivCollect.startAnimation(animation);
            mPresenter.collect();
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    public static void start(Context context, VideoInfo videoInfo) {
        Intent starter = new Intent(context, VideoInfoActivity.class);
        starter.putExtra("videoInfo", videoInfo);
        context.startActivity(starter);
    }
}