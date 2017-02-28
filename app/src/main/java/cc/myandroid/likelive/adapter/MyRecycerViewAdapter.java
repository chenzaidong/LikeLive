package cc.myandroid.likelive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.TodayStarResult;

/**
 * Created by Administrator on 2017/2/21.
 */

public class MyRecycerViewAdapter extends BaseRecyclerAdapter<TodayStarResult.ListBean> {

    private LayoutInflater inflater;
    private Context context;
    public MyRecycerViewAdapter(Context context){
        this.context=context;
        inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 绑定数据
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreat(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.new_item,null);
        return new ViewHoldor(view);
    }

    /**
     * 数据赋值
     * @param viewHolder
     * @param realPosition
     * @param data
     */
    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, TodayStarResult.ListBean data) {
        ViewHoldor viewHoldor= (ViewHoldor) viewHolder;
        Glide.with(context).load(data.getIcon()).into(viewHoldor.getImageView1());
        if (data.getIs_live()==0){
            viewHoldor.getImageView2().setImageResource(R.drawable.pc_live);//获取pc还是tel直播模式0pc,
        }else {
            viewHoldor.getImageView2().setImageResource(R.drawable.tel);
        }
        viewHoldor.getTextView1().setText(data.getTag().get(0));//获取是美女还是帅哥
        viewHoldor.getTextView2().setText(data.getNickname());//获取主播取的title
    }

    public class ViewHoldor extends Holder{
        ImageView img_head_portrait,img_tel_mode;
        TextView sex_belle,tv_title;
        public ViewHoldor(View itemView) {
            super(itemView);
            img_head_portrait= (ImageView) itemView.findViewById(R.id.img_head_portrait);
            img_tel_mode= (ImageView) itemView.findViewById(R.id.img_tel_mode);
            sex_belle= (TextView) itemView.findViewById(R.id.sex_belle);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
        }
        public ImageView getImageView1(){
            return img_head_portrait;
        }
        public ImageView getImageView2(){
            return img_tel_mode;
        }
        public TextView getTextView1(){
            return sex_belle;
        }
        public TextView getTextView2(){
            return tv_title;
        }
    }
}
