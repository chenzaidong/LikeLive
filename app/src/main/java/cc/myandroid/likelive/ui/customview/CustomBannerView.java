package cc.myandroid.likelive.ui.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.model.bean.BannerResult;


/**
 * Created by chenzd on 2017/2/22.
 * 自定义广告条
 */

public class CustomBannerView extends RelativeLayout implements ViewPager.OnPageChangeListener{
    private Context mContext;
    private ViewPager mViewPager;
    private List<ShowBannerBean> banners = new ArrayList<>();
    private LinearLayout linearLayout;//小圆点容器
    private LinearLayout.LayoutParams imgLP;//小圆点布局
    private MyAdapter mAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (banners != null && banners.size() > 0) {
                mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % banners.size());
//                for (int i = 0; i < linearLayout.getChildCount(); i++) {
//                    ImageView iv = (ImageView) linearLayout.getChildAt(i);
//                    iv.setImageResource(R.mipmap.yuan1);
//                }
//                ((ImageView) linearLayout.getChildAt(mViewPager.getCurrentItem())).setImageResource(R.mipmap.yuan2);
            }
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    public CustomBannerView(Context context) {
        this(context, null);
    }

    public CustomBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    /**
     * 初始化视图
     */
    private void init() {
        mViewPager = new ViewPager(mContext);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearLayout = new LinearLayout(getContext());
        linearLayout.setHorizontalGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(lp);
        imgLP = new LinearLayout.LayoutParams(20, 20);
        imgLP.setMargins(5, 5, 5, 5);
        mAdapter = new MyAdapter();
        mViewPager.setAdapter(mAdapter);
        addView(mViewPager);
        addView(linearLayout);
    }

    /**
     * Viewpager滚动监听，跟随Viewpager滚动，设置小圆点图片资源
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int size = banners.size();
        int childCount = linearLayout.getChildCount();
        int currentItem = mViewPager.getCurrentItem();
        for (int i = 0; i < size; i++) {
            if (childCount> 0 && linearLayout.getChildAt(i) != null) {
                ((ImageView) linearLayout.getChildAt(i)).setImageResource(R.mipmap.yuan1);
            }
        }
        if (childCount > 0 && linearLayout.getChildAt(currentItem) != null) {
            ((ImageView) linearLayout.getChildAt(currentItem)).setImageResource(R.mipmap.yuan2);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置广告数据
     * @param bannerResult
     */
    public void setData(BannerResult bannerResult){
        int size = bannerResult.getList().size();
        List<ShowBannerBean> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BannerResult.ListBean listBean = bannerResult.getList().get(i);
            //正在直播的主播才显示到广告条
            if (listBean.getType().equals("1") && listBean.getData().getIs_live() == 1) {
                ShowBannerBean banner = new ShowBannerBean();
                banner.setImg_url(listBean.getImg_url());
                banner.setRoomid(listBean.getData().getRoomid());
                results.add(banner);
            }
        }
        mAdapter.setDate(results);
        addPoint();
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    /**
     * 判断推送的广告主播时候有开始直播中的
     * @return
     */
    public boolean hasLive(BannerResult bannerResult){
        int size = bannerResult.getList().size();
        for (int i = 0; i < size; i++) {
            BannerResult.ListBean listBean = bannerResult.getList().get(i);
            if (listBean.getType().equals("1") && listBean.getData().getIs_live() == 1) {
                return true;
            }
        }
        return false;
    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return banners.size();
        }

        /**
         * 给adapter设置数据
         *
         * @param result
         */
        public void setDate(List<ShowBannerBean> result) {
            banners = result;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(banners.get(position).getImg_url()).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 添加小圆点
     */
    private void addPoint() {
        linearLayout.removeAllViews();
        for (int i = 0; i < banners.size(); i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(imgLP);
            iv.setImageResource(R.mipmap.yuan1);
            linearLayout.addView(iv);
        }
    }

    /**
     * 用于封装要显示的广告
     */
    class ShowBannerBean {
        String img_url;
        String roomid;

        public ShowBannerBean() {
        }

        public ShowBannerBean(String img_url, String type, String roomid) {
            this.img_url = img_url;
            this.roomid = roomid;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        @Override
        public String toString() {
            return "ShowBannerBean{" +
                    "img_url='" + img_url + '\'' +
                    ", roomid='" + roomid + '\'' +
                    '}';
        }
    }
}
