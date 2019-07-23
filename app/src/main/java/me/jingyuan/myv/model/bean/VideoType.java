package me.jingyuan.myv.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * desc：视频类型
 *
 * @author：degel on 2019-07-22 16:30
 */
public class VideoType {
    public String title;
    public String moreURL;
    public String pic;
    public String dataId;
    public String airTime;
    public String score;
    public String description;
    public String msg;
    public String phoneNumber;
    public String userPic;
    public String time;
    public String likeNum;
    public
    @SerializedName("childList")
    List<VideoInfo> childList;
}
