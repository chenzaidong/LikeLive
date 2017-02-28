package cc.myandroid.likelive.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.ContributionViewpagerAdapter;
import cc.myandroid.likelive.adapter.MyRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContributionFragment extends Fragment {
    private Context context;
    private List<Fragment> fraList;
    private List<String>cTitle;
    private ContributionViewpagerAdapter contributeAdapter;
    private RecyclerView recyclerview_contribute;
    private MyRecyclerAdapter myAdapter;
    private LinearLayoutManager mLayoutManager;
    ArrayList<String> data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contribution, container, false);
        init(view);
        //布局管理器
//        mLayoutManager= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        recyclerview_contribute.setLayoutManager(mLayoutManager);
        //RecyclerView Animators是一个Android类库，
        // 允许开发者轻松的为RecyclerView的item添加itemanimator，
        // 使得RecyclerView具有动画效果。可以设置动画的持续时间。
       // recyclerview_contribute.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    private void init(View view) {
        findView(view);
        initData();
        setadapter();

    }
    //找控件
    private void findView(View view) {
        recyclerview_contribute= (RecyclerView) view.findViewById(R.id.recyclerview_contribute);

    }
    //初始化数据
        public void initData(){
            for (int i = 0; i < 29; i++) {
                data.add("数据" + i);
            }
        }
    //设置监听
    public void setadapter(){

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerview_contribute.setLayoutManager(mLayoutManager);
        recyclerview_contribute.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new MyRecyclerAdapter();
        recyclerview_contribute.setAdapter(myAdapter);
        myAdapter.addDatas(data);
        setHeader(recyclerview_contribute);
        myAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
            }
        });

    }
    //设置头部
    public void setHeader(RecyclerView view){
        View header=LayoutInflater.from(context).inflate(R.layout.contribute_headlayout,view,false);
        myAdapter.setHeaderView(header);

    }







}

