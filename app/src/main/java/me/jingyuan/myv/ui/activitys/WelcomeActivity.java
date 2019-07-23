package me.jingyuan.myv.ui.activitys;


import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import me.jingyuan.myv.R;
import me.jingyuan.myv.base.BaseMvpActivity;
import me.jingyuan.myv.component.ImageLoader;
import me.jingyuan.myv.presenter.WelcomePresenter;
import me.jingyuan.myv.presenter.contract.WelcomeContract;
import me.jingyuan.myv.utils.EventUtil;
import me.jingyuan.myv.utils.StringUtils;

/**
 * Description: 开屏页
 * Creator: degel
 */
public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(List<String> list) {
        if (list != null) {
            int page = StringUtils.getRandomNumber(0, list.size() - 1);
            ImageLoader.load(mContext, list.get(page), ivWelcomeBg);
            ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        }

    }

    @Override
    public void jumpToMain() {
        MainActivity.start(this);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}