package com.wul.hlt_client.ui.classify;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.event.SwitchFlow;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.DowmTimer;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.ui.opsgood.OpsGoodActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @BindView(R.id.tixing_button)
    TextView tixingButton;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    Unbinder unbinder;
    @BindView(R.id.time_layout)
    LinearLayout timeLayout;

    private int flowSelectPosition = 0;

    private List<ClassifyBO> classifyBOS;

    private FlowAdapter adapter;

    Timer timer;

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

        initView();
        mPresenter.getCityGongGao();
        mPresenter.getClassifyList();
    }


    /**
     * 初始化布局
     */
    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        zhuClassifyRecycle.setLayoutManager(manager);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        congRecycle.setLayoutManager(manager1);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager2);
        changyongLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changyong_layout:
                gotoActivity(OpsGoodActivity.class, false);
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SwitchFlow flow) {
        if (adapter != null) {
            adapter.setSelectPosition(flow.position);
            flowSelectPosition = flow.position;
            mPresenter.getChildClassify(classifyBOS.get(flowSelectPosition).getId());
        }
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
            mPresenter.getChildClassify(list.get(0).getId());
        }
        adapter = new FlowAdapter(getActivity(), list);
        adapter.setOnItemClickListener(R.id.flow_text, (view, position) -> {
            adapter.setSelectPosition(position);
            flowSelectPosition = position;
            mPresenter.getChildClassify(list.get(position).getId());
        });
        zhuClassifyRecycle.setAdapter(adapter);
    }

    @Override
    public void getChildClassify(List<ClassifyBO> list) {
        if (list != null && list.size() > 0) {
            mPresenter.getXianshiList(classifyBOS.get(flowSelectPosition).getId(), list.get(0).getId());
        }
        ChildFlowAdapter adapter = new ChildFlowAdapter(getActivity(), list);
        adapter.setOnItemClickListener(R.id.flow_child_text, (view, position) -> {
            adapter.setSelectPosition(position);
            mPresenter.getXianshiList(classifyBOS.get(flowSelectPosition).getId(), list.get(position).getId());
        });
        congRecycle.setAdapter(adapter);
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
        timer.schedule(new DowmTimer(shopBOS.getStartTime(), shopBOS.getEndTime(), handler), 0, 1000);
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


}
