package me.jingyuan.myv.model.http;

import java.util.List;

import javax.inject.Inject;

import me.jingyuan.myv.model.bean.GankItemBean;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.http.api.GankApis;
import me.jingyuan.myv.model.http.api.VideoApis;
import me.jingyuan.myv.model.http.response.GankHttpResponse;
import me.jingyuan.myv.model.http.response.VideoHttpResponse;
import rx.Observable;

/**
 * desc：me.jingyuan.myv.model.http
 *
 * @author：degel on 2019-07-22 16:38
 */
public class RetrofitHelper implements HttpHelper {
    public static final String PATH = "福利";


    private VideoApis mVideoApis;
    private GankApis mGankApis;

    @Inject
    public RetrofitHelper(VideoApis videoApis, GankApis gankApis) {
        this.mVideoApis = videoApis;
        this.mGankApis = gankApis;
    }


    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchHomePage() {

        return mVideoApis.getHomePage();
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoInfo(String mediaId) {
        return mVideoApis.getVideoInfo(mediaId);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoList(String catalogId, String pnum) {
        return mVideoApis.getVideoList(catalogId, pnum);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoListByKeyWord(String keyword, String pnum) {
        return mVideoApis.getVideoListByKeyWord(keyword, pnum);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchCommentList(String mediaId, String pnum) {
        return mVideoApis.getCommentList(mediaId, pnum);
    }

    @Override
    public Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return mGankApis.getGirlList(PATH,num, page);
    }
}
