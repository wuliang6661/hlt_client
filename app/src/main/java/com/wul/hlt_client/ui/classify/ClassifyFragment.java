package com.wul.hlt_client.ui.classify;


import android.os.Bundle;
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
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.ui.opsgood.OpsGoodActivity;

import java.util.List;

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

    private int flowSelectPosition = 0;

    private List<ClassifyBO> classifyBOS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_classify, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        FlowAdapter adapter = new FlowAdapter(getActivity(), list);
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
    public void getXianshiList(List<ShopBO> shopBOS) {
        ShopAdapter adapter = new ShopAdapter(getActivity(), shopBOS);
        recycle.setAdapter(adapter);
    }
}
