package cc.myandroid.likelive.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.cyberplayer.core.BVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;

/**
 * Created by chenzd on 2017/2/28.
 * 该Fragment用于对直播或观看直播的初始化
 * 直播的控件的创建以及销毁等等都可以在这里进行操作，这样就与我们自己的交互代码分离了
 */
public class LiveViewFragment extends Fragment {

    private Context mContext;
    @BindView(R.id.baidu_video_view)
    BVideoView baiduVideoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liveview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String video_url = getArguments().getString("video_url");
        if (video_url != null) {
            startVideo(video_url);
        }
    }


    /**
     * 播放直播
     *
     * @param video_url
     */
    private void startVideo(String video_url) {
        //设置视频的缩放模式
        baiduVideoView.setVideoScalingMode(BVideoView.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        /**
         * 设置解码模式 为保证兼容性，当前仅支持软解
         */
        baiduVideoView.setDecodeMode(BVideoView.DECODE_SW);
        baiduVideoView.selectResolutionType(BVideoView.RESOLUTION_TYPE_AUTO);
        baiduVideoView.setVideoPath(video_url);
        baiduVideoView.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (baiduVideoView != null && baiduVideoView.isPlaying()) {
            baiduVideoView.pause();
            baiduVideoView.stopPlayback();
        }
    }
}