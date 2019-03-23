package com.wul.hlt_client.ui.classify;


import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.event.FinishEvent;
import com.wul.hlt_client.entity.event.SwitchFlow;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.DowmTimer;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.ui.main.MainActivity;
import com.wul.hlt_client.ui.opsgood.OpsGoodActivity;
import com.wul.hlt_client.ui.select.SelectActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.gyf.barlibrary.ImmersionBar.getStatusBarHeight;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClassifyFragment extends MVPBaseFragment<ClassifyContract.View, ClassifyPresenter>
        implements ClassifyContract.View, View.OnClickListener {

    @BindView(R.id.edit_select)
    TextView editSelect;
    @BindView(R.id.gonggao_text)
    TextView gonggaoText;
    @BindView(R.id.changyong_layout)
    LinearLayout changyongLayout;
    @BindView(R.id.zhu_classify_recycle)
    RecyclerView zhuClassifyRecycle;
    @BindView(R.id.cong_recycle)
    RecyclerView congRecycle;
    @BindView(R.id.down_time_text)
    TextView downTimeText;
    @BindView(R.id.down_time)
    TextView downTime;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    Unbinder unbinder;
    @BindView(R.id.time_layout)
    LinearLayout timeLayout;

    private int flowSelectPosition = 0;

    private List<ClassifyBO> classifyBOS;

    private FlowAdapter adapter;

    Timer timer;

    LinearLayoutManager manager;
    LinearLayoutManager manager1;

    public static ClassifyFragment getInstanse(int flowSelectPosition) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", flowSelectPosition);
        ClassifyFragment fragment = new ClassifyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_classify, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        flowSelectPosition = getArguments().getInt("key");
        initView();
        mPresenter.getClassifyList();
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.getCityGongGao();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * 初始化布局
     */
    private void initView() {
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        zhuClassifyRecycle.setLayoutManager(manager);
        manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        congRecycle.setLayoutManager(manager1);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager2);
        changyongLayout.setOnClickListener(this);
        editSelect.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changyong_layout:
                start(new OpsGoodActivity());
                break;
            case R.id.edit_select:
                start(new SelectActivity());
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SwitchFlow flow) {
        flowSelectPosition = flow.position;
        if (adapter != null) {
            adapter.setSelectPosition(flow.position);
            mPresenter.getChildClassify(classifyBOS.get(flowSelectPosition).getId());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FinishEvent event) {
        pop();
    }


    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getCityGongGao(List<CityGongGao> cityGongGaos) {
        StringBuilder buffer = new StringBuilder();
        for (CityGongGao cityGongGao : cityGongGaos) {
            buffer.append(cityGongGao.getContent()).append("                      ");
        }
        gonggaoText.setText(buffer.toString());
    }

    @Override
    public void getClassify(List<ClassifyBO> list) {
        this.classifyBOS = list;
        if (list != null && list.size() > 0) {
            mPresenter.getChildClassify(list.get(flowSelectPosition).getId());
        }
        adapter = new FlowAdapter(getActivity(), list);
        adapter.setOnItemClickListener(R.id.flow_text, (view, position) -> {
            adapter.setSelectPosition(position);
            flowSelectPosition = position;
            if (isVisible(position)) {
                scrollToMiddleW(view, position);
            }
            mPresenter.getChildClassify(list.get(position).getId());
        });
        adapter.setSelectPosition(flowSelectPosition);
        zhuClassifyRecycle.setAdapter(adapter);
        zhuClassifyRecycle.scrollToPosition(flowSelectPosition);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollToMiddleW(manager.findViewByPosition(flowSelectPosition), flowSelectPosition);
            }
        }, 300);
    }


    private boolean isVisible(int position) {//所点击的 Item是不是在屏幕位置中可见
        final int firstPosition = manager.findFirstVisibleItemPosition();//第一个可见的Item 位置值
        final int lastPosition = manager.findLastVisibleItemPosition();//最后一个可见的Item 位置值
        return position <= lastPosition && position >= firstPosition;
    }


    private void scrollToMiddleW(View view, int position) {
        int vWidth = view.getWidth();
        Rect rect = new Rect();
        zhuClassifyRecycle.getGlobalVisibleRect(rect);
        int reWidth = rect.right - rect.left - vWidth; //除掉点击View的宽度，剩下整个屏幕的宽度
        final int firstPosition = manager.findFirstVisibleItemPosition();
        int left = zhuClassifyRecycle.getChildAt(position - firstPosition).getLeft();//从左边到点击的Item的位置距离
        int half = reWidth / 2;//半个屏幕的宽度
        int moveDis = left - half;//向中间移动的距离
        zhuClassifyRecycle.smoothScrollBy(moveDis, 0);
    }


    private int childCount = 0;
    private int middlechild = 0;

    @Override
    public void getChildClassify(List<ClassifyBO> list) {
        if (list != null && list.size() > 0) {
            mPresenter.getXianshiList(classifyBOS.get(flowSelectPosition).getId(), list.get(0).getId());
        }
        ChildFlowAdapter adapter = new ChildFlowAdapter(getActivity(), list);
        adapter.setOnItemClickListener(R.id.flow_child_text, (view, position) -> {
            adapter.setSelectPosition(position);
            mPresenter.getXianshiList(classifyBOS.get(flowSelectPosition).getId(), list.get(position).getId());

//            if(isvisible(position)){
//                scrollToMiddleH(view,position);
//            }

//            //得到布局
//            RecyclerView.LayoutManager manager = congRecycle.getLayoutManager();
//            //竖排类型,所以强转LinearLayoutManager,如果是ListView就不需要强转
//            LinearLayoutManager layoutManager = (LinearLayoutManager) manager;
//
//            //得到屏幕可见的item的总数
//            childCount = layoutManager.getChildCount();
//            if (childCount != list.size()) {
//                //可见item的总数除以2  就可以拿到中间位置
//                middlechild = childCount / 2;
//            }
//
//            //判断你点的是中间位置的上面还是中间的下面位置
//            //RecyclerView必须加 && position != 2,listview不需要
//            if (position <= (layoutManager.findFirstVisibleItemPosition() + middlechild) && position != 2) {
//                Log.e("wuliang", (position + 1 - middlechild) + "");
////                if (position + 1 - middlechild >= 0) {
//                    congRecycle.smoothScrollToPosition(Math.abs(position + 1 - middlechild));
////                }else{
////                    congRecycle.smoothScrollToPosition(position - 1 + middlechild);
////                }
//            } else {
//                congRecycle.smoothScrollToPosition(position - 1 + middlechild);
//            }
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int heightPixels = displayMetrics.heightPixels;
            int widthPixels = displayMetrics.widthPixels;
            int[] outLocation = new int[2];
            view.getLocationOnScreen(outLocation);
            int itemLayoutHeight = outLocation[1] - getStatusBarHeight(getActivity());
            int centreHeight = (int) ((heightPixels / 2) - ((widthPixels * 0.5) / 2));

            RecyclerView.LayoutManager layoutManager = congRecycle.getLayoutManager();
            if (layoutManager != null && layoutManager instanceof LinearLayoutManager) {
                final LinearLayoutManager mLayoutManager = (LinearLayoutManager) layoutManager;
                if (centreHeight != itemLayoutHeight) {
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(itemLayoutHeight, centreHeight);
                    valueAnimator.setDuration(100);
                    valueAnimator.setInterpolator(new LinearInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int animatedValue = (int) animation.getAnimatedValue();
                            ((LinearLayoutManager) mLayoutManager).scrollToPositionWithOffset(position, animatedValue);

                        }
                    });
                    valueAnimator.start();
                }
            }
        });
        congRecycle.setAdapter(adapter);
    }


    private boolean isvisible(int position) {//所点击的 Item是不是在屏幕位置中可见

        final int firstPosition = manager1.findFirstVisibleItemPosition();//第一个可见的Item 位置值

        final int lastPosition = manager1.findLastVisibleItemPosition();//最后一个可见的Item 位置值

        return position <= lastPosition && position >= firstPosition;

    }


    private void scrollToMiddleH(View view, int position) {
        int vHeight = view.getHeight();
        Rect rect = new Rect();
        congRecycle.getGlobalVisibleRect(rect);
        int reHeight = rect.top - rect.bottom - vHeight;
        final int firstPosition = manager1.findFirstVisibleItemPosition();
        int top = congRecycle.getChildAt(position - firstPosition).getTop();
        int half = reHeight / 2;
        congRecycle.smoothScrollBy(0, top - half);
    }


    @Override
    public void getXianshiList(XianShiBO shopBOS) {
        if (shopBOS.getStartTime() == 0 || shopBOS.getEndTime() == 0) {
            timeLayout.setVisibility(View.GONE);
        } else {
            timeLayout.setVisibility(View.VISIBLE);
        }
        ShopAdapter adapter = new ShopAdapter(getActivity(), shopBOS.getList(), MyApplication.shopCarBO);
        recycle.setAdapter(adapter);
        timer = new Timer();
        timer.schedule(new DowmTimer(getActivity(), shopBOS.getStartTime(), shopBOS.getEndTime(), handler), 0, 1000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String time = (String) msg.obj;
            switch (msg.what) {
                case 0x11:
                    if (downTimeText != null) {
                        downTimeText.setText("距离秒杀活动开始还有：");
                    }
                    break;
                case 0x22:
                    if (downTimeText != null) {
                        downTimeText.setText("距离秒杀活动结束还有：");
                    }
                    break;
                case 0x33:
//                    mPresenter.getXianshiList();
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    break;
            }
            if (downTime != null) {
                downTime.setText(time);
            }
        }
    };


    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        if (timer != null) {
            timer.cancel();
        }
        ImmersionBar.with(this).destroy();
        super.onDestroy();
    }

    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.green_color)
                .statusBarDarkFont(true).keyboardEnable(true).init();   //解决虚拟按键与状态栏沉浸冲突
    }

    private boolean isImmersionBarEnabled() {
        return true;
    }


}
