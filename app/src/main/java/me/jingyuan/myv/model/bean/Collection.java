package me.jingyuan.myv.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * 描述：收藏
 * 作者：degel on 2019-07-22 16:19
 */
public class Collection extends RealmObject implements Serializable {
    String id;
    long time;
    public String title;
    public String pic;
    public String airTime;
    public String score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
