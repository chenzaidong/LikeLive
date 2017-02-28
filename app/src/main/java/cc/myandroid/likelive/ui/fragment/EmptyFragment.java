package cc.myandroid.likelive.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.myandroid.likelive.R;

/**
 * Created by chenzd on 2017/2/28.
 * 该Fragment是用于MainDialogFragment中的pager，为了实现滑动隐藏交互Fragment的
 */
public class EmptyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }
}