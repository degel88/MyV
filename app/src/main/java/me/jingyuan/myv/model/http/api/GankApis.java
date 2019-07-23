package me.jingyuan.myv.model.http.api;

import java.util.List;

import me.jingyuan.myv.model.bean.GankItemBean;
import me.jingyuan.myv.model.http.response.GankHttpResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * desc：me.jingyuan.myv.model.http.api
 *
 * @author：degel on 2019-07-22 16:40
 */
public interface GankApis {
    String HOST = "https://gank.io/api/";

    /**
     * 福利列表
     */
    @GET("data/{path}/{num}/{page}")
    Observable<GankHttpResponse<List<GankItemBean>>> getGirlList(@Path(value = "path", encoded = false) String path, @Path("num") int num, @Path("page") int page);

}
