package me.jingyuan.myv.model.http.response;

/**
 * desc：me.jingyuan.myv.model.http.response
 *
 * @author：degel on 2019-07-22 16:34
 */
public class VideoHttpResponse<T> {

    private int code;
    private String msg;
    T ret;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }
}
