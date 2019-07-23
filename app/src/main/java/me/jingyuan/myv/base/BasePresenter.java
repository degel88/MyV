package me.jingyuan.myv.base;

/**
 * Description: BasePresenter
 * Creator: degel
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
