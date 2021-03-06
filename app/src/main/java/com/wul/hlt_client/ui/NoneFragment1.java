package com.wul.hlt_client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wul.hlt_client.R;
import com.wul.hlt_client.ui.main.home.HomeFragment;
import com.wul.hlt_client.ui.main.salesgood.SalesGoodFragment;
import com.wul.hlt_client.ui.main.shopcar.ShopCarFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by wuliang on 2018/12/29.
 * <p>
 * 容器Fragment
 */

public class NoneFragment1 extends SupportFragment {


    public static NoneFragment1 newInstance() {
        Bundle args = new Bundle();
        NoneFragment1 fragment = new NoneFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_load, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, new HomeFragment());
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
