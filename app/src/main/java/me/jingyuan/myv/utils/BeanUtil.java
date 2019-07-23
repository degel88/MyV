package me.jingyuan.myv.utils;

import me.jingyuan.myv.model.bean.VideoInfo;
import me.jingyuan.myv.model.bean.VideoRes;
import me.jingyuan.myv.model.bean.VideoType;

/**
 * 作者：degel on 2019-07-22 15:50
 */
public class BeanUtil {
    public static VideoInfo VideoType2VideoInfo(VideoType videoType, VideoInfo videoInfo) {
        if (videoInfo == null) {
            videoInfo = new VideoInfo();
        }
        videoInfo.title = videoType.title;
        videoInfo.dataId = videoType.dataId;
        videoInfo.pic = videoType.pic;
        videoInfo.airTime = videoType.airTime;
        videoInfo.score = videoType.score;
        return videoInfo;
    }

    public static VideoRes VideoInfo2VideoRes(VideoInfo videoInfo, VideoRes videoRes) {
        if (videoRes == null) {
            videoRes = new VideoRes();
        }
        videoRes.title = StringUtils.isEmpty(videoInfo.title);
        videoRes.pic = StringUtils.isEmpty(videoInfo.pic);
        videoRes.score = StringUtils.isEmpty(videoInfo.score);
        videoRes.airTime = StringUtils.isEmpty(videoInfo.airTime);
        videoRes.pic = StringUtils.isEmpty(videoInfo.pic);
        return videoRes;
    }
}
