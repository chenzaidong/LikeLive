package cc.myandroid.likelive.callback;
import java.util.List;
/**
 * Created by chenzd on 2017/2/19.
 */

public interface ListCallback<T> {
        void success(List<T> data);
        void failed(String result);
}
