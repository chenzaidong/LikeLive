package cc.myandroid.likelive.presenter;

import java.util.List;

import cc.myandroid.likelive.api.RoomInfoContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.model.bean.CurrentTuhaoResult;
import cc.myandroid.likelive.model.bean.RoomInfoResult;
import cc.myandroid.likelive.model.bean.WeekTuhaoResult;
import cc.myandroid.likelive.model.impl.RoomInfoModel;
import cc.myandroid.likelive.utils.StringFormatUtil;

/**
 * Created by chenzd on 2017/2/19.
 */

public class RoomInfoPresenter implements RoomInfoContract.IRoomInfoPresenter {
   private RoomInfoContract.IRoomInfoModel iRoomInfoModel;
    private RoomInfoContract.IRoomInfoView iRoomInfoView;
    public RoomInfoPresenter(RoomInfoContract.IRoomInfoView iRoomInfoView) {
        iRoomInfoModel = new RoomInfoModel();
        this.iRoomInfoView = iRoomInfoView;
    }

    /**
     * 获取场贡献榜
     */
    @Override
    public void getCurrentTuhaoList(int roomId) {
        iRoomInfoModel.getCurrentTuhaoList(roomId, new ListCallback<CurrentTuhaoResult>() {
            @Override
            public void success(List<CurrentTuhaoResult> data) {
                iRoomInfoView.onGetCurrentTuhaoList(data);
            }
            @Override
            public void failed(String result) {
                iRoomInfoView.onGetFailed(result);
            }
        });
    }
    /**
     * 获取周贡献榜
     */
    @Override
    public void getWeekTuhaoList(int roomId) {
        iRoomInfoModel.getWeekTuhaoList(roomId, new ListCallback<WeekTuhaoResult>() {
            @Override
            public void success(List<WeekTuhaoResult> data) {
                iRoomInfoView.onGetWeekTuhaoList(data);
            }

            @Override
            public void failed(String result) {
                iRoomInfoView.onGetFailed(result);
            }
        });
    }

    @Override
    public void getRoomInfo(int roomId) {
        iRoomInfoModel.getRoomInfo(roomId,new Callback<RoomInfoResult>() {

            @Override
            public void success(RoomInfoResult data) {
                String shareStr = data.getShare_description();
                shareStr = StringFormatUtil.replace(shareStr,"酒窝","爱直播");
                data.setShare_description(shareStr);
                iRoomInfoView.onGetRoomInfo(data);
            }

            @Override
            public void failed(String result) {
                iRoomInfoView.onGetFailed(result);
            }
        });
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        iRoomInfoModel.onUnsubscribe();
    }
}
