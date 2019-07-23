package me.jingyuan.myv.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * 描述：播放记录
 * 作者：degel on 2019-07-22 15:53
 */
public class Record extends RealmObject implements Serializable {
    public String title;
    public String pic;
    String id;
    long time;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
