package com.wul.hlt_client.ui.main.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkingliang.banner.CustomBanner;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.GlideApp;
import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.event.SwithFragment;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.DowmTimer;
import com.wul.hlt_client.ui.MediaListener;
import com.wul.hlt_client.ui.classify.ClassifyFragment;
import com.wul.hlt_client.ui.opsgood.OpsGoodActivity;
import com.wul.hlt_client.ui.select.SelectActivity;
import com.wul.hlt_client.util.NotificationUtils;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter>
        implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    @BindView(R.id.edit_select)
    TextView editSelect;
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
    @BindView(R.id.down_time_text)
    TextView downTimeText;

    Timer timer;
    @BindView(R.id.xianshi_layout)
    LinearLayout xianshiLayout;
    @BindView(R.id.none1)
    LinearLayout none1;
    @BindView(R.id.none2)
    LinearLayout none2;


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
        changyongMore.setOnClickListener(this);
        xianshiMore.setOnClickListener(this);
        editSelect.setOnClickListener(this);
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
        mBanner.setOnPageClickListener((i, o) -> {

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
        adapter.setOnItemClickListener(R.id.item_layout, (view, position) -> {
            start(ClassifyFragment.getInstanse(position));
        });
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
        if (list == null || list.size() == 0) {
            changyongRecycle.setVisibility(View.GONE);
            none2.setVisibility(View.VISIBLE);
            return;
        } else {
            changyongRecycle.setVisibility(View.VISIBLE);
            none2.setVisibility(View.GONE);
        }
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
    public void getXianshiList(XianShiBO list) {
        swipe.setRefreshing(false);
        if (list.getList() == null || list.getList().size() == 0) {
            xianshiRecycle.setVisibility(View.GONE);
            none1.setVisibility(View.VISIBLE);
            downTimeText.setText("暂无促销活动：");
            return;
        } else {
            xianshiRecycle.setVisibility(View.VISIBLE);
            none1.setVisibility(View.GONE);
        }
        LGRecycleViewAdapter<ShopBO> adapter = new LGRecycleViewAdapter<ShopBO>(list.getList()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_xianshi_menu;
            }

            @Override
            public void convert(LGViewHolder holder, ShopBO classifyBO, int position) {
                holder.setImageUrl(getActivity(), R.id.item_img, classifyBO.getImage());
                holder.setText(R.id.item_text, classifyBO.getProductName());
                TextView oldPrice = (TextView) holder.getView(R.id.shop_old_price);
                oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                oldPrice.setText("¥ " + classifyBO.getPrice1() + "元/" + classifyBO.getMeasureUnitName1());
                holder.setText(R.id.shop_price, "¥" + classifyBO.getPromotionPrice1() + "元/" + classifyBO.getMeasureUnitName1());
            }
        };
        xianshiRecycle.setAdapter(adapter);
        timer = new Timer();
        timer.schedule(new DowmTimer(getActivity(), list.getStartTime(), list.getEndTime(), handler), 0, 1000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String time = (String) msg.obj;
            switch (msg.what) {
                case 0x11:
                    downTimeText.setText("距离开始时间还有：");
                    break;
                case 0x22:
                    downTimeText.setText("距离结束时间还有：");
                    break;
                case 0x33:
                    mPresenter.getXianshiList();
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    break;
            }
            downTime.setText(time);
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
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
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changyong_more:   //常用列表
                gotoActivity(OpsGoodActivity.class, false);
                break;
            case R.id.xianshi_more:    //进入限时抢购
                EventBus.getDefault().post(new SwithFragment(1));
                break;
            case R.id.edit_select:    //进入搜索页面
                gotoActivity(SelectActivity.class, false);
//                NotificationUtils.showNotification(getActivity());
                break;
        }
    }
}
