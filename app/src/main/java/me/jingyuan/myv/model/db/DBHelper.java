package me.jingyuan.myv.model.db;

import java.util.List;

import io.realm.Realm;
import me.jingyuan.myv.model.bean.Record;
import me.jingyuan.myv.model.bean.SearchKey;
import me.jingyuan.myv.model.bean.Collection;


/**
 * 描述：me.jingyuan.myv.model.db
 * 作者：degel on 2019-07-22 15:50
 */
public interface DBHelper {
    Realm getRealm();

    void insertCollection(Collection bean);

    void deleteCollection(String id);

    void deleteAllCollection();

    boolean queryCollectionId(String id);

    List<Collection> getCollectionList();

    void insertRecord(Record bean, int maxSize);

    void deleteRecord(String id);

    boolean queryRecordId(String id);

    List<Record> getRecordList();

    void deleteAllRecord();

    void insertSearchHistory(SearchKey bean);

    List<SearchKey> getSearchHistoryList(String value);

    void deleteSearchHistoryList(String value);

    void deleteSearchHistoryAll();

    List<SearchKey> getSearchHistoryListAll();
}
