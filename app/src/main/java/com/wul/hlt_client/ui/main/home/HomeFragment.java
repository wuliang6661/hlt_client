package com.wul.hlt_client.ui.main.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.donkingliang.banner.CustomBanner;
import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter>
        implements HomeContract.View {


    @BindView(R.id.edit_select)
    EditText editSelect;
    @BindView(R.id.classify_recycle)
    RecyclerView classifyRecycle;
    @BindView(R.id.down_time)
    TextView downTime;
    @BindView(R.id.xianshi_more)
    TextView xianshiMore;
    @BindView(R.id.xianshi_recycle)
    RecyclerView xianshiRecycle;
    @BindView(R.id.changyong_more)
    TextView changyongMore;
    @BindView(R.id.changyong_recycle)
    RecyclerView changyongRecycle;
    @BindView(R.id.banner)
    CustomBanner mBanner;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fra_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter.getClassifyList();
        mPresenter.getBanner();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 初始化布局
     */
    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        classifyRecycle.setLayoutManager(manager);
        classifyRecycle.setNestedScrollingEnabled(false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        xianshiRecycle.setLayoutManager(manager1);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        changyongRecycle.setLayoutManager(manager2);
    }


    /**
     * 设置轮播图
     */
    private void setBanner() {
//        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
//            @Override
//            public View createView(Context context, int position) {
//                //这里返回的是轮播图的项的布局 支持任何的布局
//                //position 轮播图的第几个项
//                ImageView imageView = new ImageView(context);
//                return imageView;
//            }
//
//            @Override
//            public void updateUI(Context context, View view, int position, String data) {
//                //在这里更新轮播图的UI
//                //position 轮播图的第几个项
//                //view 轮播图当前项的布局 它是createView方法的返回值
//                //data 轮播图当前项对应的数据
//                GlideApp.with(context).load(data).into((ImageView) view);
//            }
//        }, beans);
        //设置轮播图自动滚动轮播，参数是轮播图滚动的间隔时间
        //轮播图默认是不自动滚动的，如果不调用这个方法，轮播图将不会自动滚动。
        mBanner.startTurning(3600);
        //停止轮播图的自动滚动
        mBanner.stopTurning();
        //设置轮播图的滚动速度
        mBanner.setScrollDuration(500);
        //设置轮播图的点击事件
        mBanner.setOnPageClickListener(new CustomBanner.OnPageClickListener<String>() {
            @Override
            public void onPageClick(int position, String str) {
                //position 轮播图的第几个项
                //str 轮播图当前项对应的数据
            }
        });
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }
}
