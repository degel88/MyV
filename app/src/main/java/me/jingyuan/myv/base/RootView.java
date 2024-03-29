package me.jingyuan.myv.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by degel
 */
public abstract class RootView<T extends BasePresenter> extends View implements BaseView {
    /**
     * //是否被销毁
     */
    protected boolean mActive;
    protected Context mContext;
    protected Unbinder unbinder;
    protected T mPresenter;

    public RootView(Context context) {
        super(context);
        init();
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected abstract void getLayout();

    protected abstract void initView();

    protected abstract void initEvent();

    protected void init() {
        mContext = getContext();
        getLayout();
        unbinder = ButterKnife.bind(this);
        mActive = true;
        initView();
        initEvent();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mActive = false;
        unbinder.unbind();
        mContext = null;
    }
}

