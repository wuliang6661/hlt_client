package com.wul.hlt_client.ui.main.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.banner.CustomBanner;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.GlideApp;
import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter>
        implements HomeContract.View ,SwipeRefreshLayout.OnRefreshListener{


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
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

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
        invitionSwipeRefresh(swipe);
        swipe.setOnRefreshListener(this);
        mPresenter.getClassifyList();
        mPresenter.getBanner();
        mPresenter.getComomPaseList();
        mPresenter.getXianshiList();
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
        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 4);
        xianshiRecycle.setLayoutManager(manager1);
        xianshiRecycle.setNestedScrollingEnabled(false);
        GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 4);
        changyongRecycle.setLayoutManager(manager2);
        changyongRecycle.setNestedScrollingEnabled(false);
    }


    /**
     * 设置轮播图
     */
    private void setBanner(List<BannerBo> list) {
        mBanner.setPages(new CustomBanner.ViewCreator<BannerBo>() {
            @Override
            public View createView(Context context, int position) {
                RoundedImageView imageView = new RoundedImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setCornerRadius(10);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, BannerBo data) {
                GlideApp.with(context).load(data.getImage())
                        .placeholder(R.drawable.zhanwei1)
                        .error(R.drawable.zhanwei1)
                        .into((RoundedImageView) view);
            }
        }, list);
        //设置轮播图自动滚动轮播，参数是轮播图滚动的间隔时间
        //轮播图默认是不自动滚动的，如果不调用这个方法，轮播图将不会自动滚动。
        // mBanner.startTurning(3600);
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

    /**
     * 返回上半部分类型
     */
    public void getClassify(List<ClassifyBO> list) {
        swipe.setRefreshing(false);
        LGRecycleViewAdapter<ClassifyBO> adapter = new LGRecycleViewAdapter<ClassifyBO>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_home_classify;
            }

            @Override
            public void convert(LGViewHolder holder, ClassifyBO classifyBO, int position) {
                holder.setImageUrl(getActivity(), R.id.item_img, classifyBO.getImage());
                holder.setText(R.id.item_text, classifyBO.getCategoryName());
            }
        };
        classifyRecycle.setAdapter(adapter);
    }

    @Override
    public void getBannerList(List<BannerBo> list) {
        swipe.setRefreshing(false);
        setBanner(list);
    }

    /**
     * 常用清单适配器
     */
    @Override
    public void getChangyongShop(List<ShopBO> list) {
        swipe.setRefreshing(false);
        LGRecycleViewAdapter<ShopBO> adapter = new LGRecycleViewAdapter<ShopBO>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_xianshi_menu;
            }

            @Override
            public void convert(LGViewHolder holder, ShopBO classifyBO, int position) {
                holder.setImageUrl(getActivity(), R.id.item_img, classifyBO.getImage());
                holder.setText(R.id.item_text, classifyBO.getProductName());
                holder.getView(R.id.shop_price).setVisibility(View.GONE);
                holder.setText(R.id.shop_old_price, "¥ " + classifyBO.getPrice1() + "元/" + classifyBO.getMeasureUnitName1());
            }
        };
        changyongRecycle.setAdapter(adapter);
    }

    /**
     * 限时抢购
     */
    @Override
    public void getXianshiList(List<ShopBO> list) {
        swipe.setRefreshing(false);
        LGRecycleViewAdapter<ShopBO> adapter = new LGRecycleViewAdapter<ShopBO>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_xianshi_menu;
            }

            @Override
            public void convert(LGViewHolder holder, ShopBO classifyBO, int position) {
                holder.setImageUrl(getActivity(), R.id.item_img, classifyBO.getImage());
                holder.setText(R.id.item_text, classifyBO.getProductName());
                holder.getView(R.id.shop_price).setVisibility(View.GONE);
                holder.setText(R.id.shop_old_price, "¥ " + classifyBO.getPrice1() + "元/" + classifyBO.getMeasureUnitName1());
            }
        };
        xianshiRecycle.setAdapter(adapter);
    }


    @Override
    public void onRequestError(String msg) {
        swipe.setRefreshing(false);
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        mPresenter.getClassifyList();
        mPresenter.getBanner();
        mPresenter.getComomPaseList();
        mPresenter.getXianshiList();
    }
}
