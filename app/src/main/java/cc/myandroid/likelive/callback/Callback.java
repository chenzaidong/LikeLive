package cc.myandroid.likelive.callback;

/**
 * Created by chenzd on 2017/2/19.
 */

public interface Callback<T> {
    void success(T data);

    void failed(String result);
}
