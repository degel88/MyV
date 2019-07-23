package me.jingyuan.myv.model.net;


import me.jingyuan.myv.model.exception.ApiException;
import rx.Subscriber;

/**
 * Created by degel
 */
public abstract class MyObserver<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, 123));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
