package cc.myandroid.likelive.api;

import java.util.List;

import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.model.bean.CurrentTuhaoResult;
import cc.myandroid.likelive.model.bean.RoomInfoResult;
import cc.myandroid.likelive.model.bean.WeekTuhaoResult;

/**
 * Created by chenzd on 2017/2/19.
 * 房间信息模块接口
 */

public class RoomInfoContract {
    public interface IRoomInfoView{
        void onGetCurrentTuhaoList(List<CurrentTuhaoResult> data);//获取场贡献榜
        void onGetWeekTuhaoList(List<WeekTuhaoResult> data);//获取周贡献榜
        void onGetRoomInfo(RoomInfoResult data);
        void focus();//关注
        void share();//分享
        void onGetFailed(String message);//获取失败
    }


    public interface IRoomInfoPresenter{
        void getCurrentTuhaoList(int roomId);//获取场贡献榜
        void getWeekTuhaoList(int roomId);//获取周贡献榜
        void getRoomInfo(int roomId);//获得房间信息
        void onStart();
        void onStop();
    }
    public interface IRoomInfoModel{
        void getCurrentTuhaoList(int roomId,ListCallback callback);//获取场贡献榜
        void getWeekTuhaoList(int roomId,ListCallback callback);//获取周贡献榜

        void onUnsubscribe();
        void getRoomInfo(int roomId, Callback callback);//获得房间信息
    }
}
