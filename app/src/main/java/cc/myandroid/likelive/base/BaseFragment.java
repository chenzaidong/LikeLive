package cc.myandroid.likelive.base;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by chenzd on 2017/2/22.
 */

public  abstract class BaseFragment<T> extends Fragment {
    /**
     * 初始化
     * @param datas
     */
    public abstract void initFragment(List<T> datas);

    /**
     * 根据新的数据对象 刷新UI
     */
    public abstract void updateData(List<T> datas);
}
