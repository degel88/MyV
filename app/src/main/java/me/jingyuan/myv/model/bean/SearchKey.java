package me.jingyuan.myv.model.bean;

import io.realm.RealmObject;

/**
 * 描述：me.jingyuan.myv.model.bean
 * 作者：degel on 2019-07-22 15:55
 */
public class SearchKey extends RealmObject {
    public String searchKey;
    /*
        插入时间
    */
    public long insertTime;

    public SearchKey() {
    }

    public SearchKey(String suggestion, long insertTime) {
        this.searchKey = suggestion;
        this.insertTime = insertTime;
    }

    public String getSearchKey() {
        return searchKey;
    }
}
