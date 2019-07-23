package me.jingyuan.myv.model.http.response;

/**
 * desc：me.jingyuan.myv.model.http.response
 *
 * @author：degel on 2019-07-22 16:36
 */
public class GankHttpResponse<T> {

    private boolean error;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
