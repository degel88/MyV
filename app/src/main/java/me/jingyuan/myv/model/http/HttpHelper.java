package me.jingyuan.myv.model.http;

import java.util.List;

import me.jingyuan.myv.model.bean.GankItemBean;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.http.response.GankHttpResponse;
import me.jingyuan.myv.model.http.response.VideoHttpResponse;
import rx.Observable;

/**
 * desc：me.jingyuan.myv.model
 * @author：degel on 2019-07-22 16:26
 */
public interface HttpHelper {
    Observable<VideoHttpResponse<VideoRes>> fetchHomePage();

    Observable<VideoHttpResponse<VideoRes>> fetchVideoInfo(String mediaId);

    Observable<VideoHttpResponse<VideoRes>> fetchVideoList(String catalogId, String pnum);

    Observable<VideoHttpResponse<VideoRes>> fetchVideoListByKeyWord(String keyword, String pnum);

    Observable<VideoHttpResponse<VideoRes>> fetchCommentList(String mediaId, String pnum);

    Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page);
}
