package com.wul.hlt_client.ui.tousu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.util.EditFilter;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TousuActivity extends MVPBaseActivity<TousuContract.View, TousuPresenter>
        implements TousuContract.View, View.OnClickListener {

    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.edit_tousu)
    EditText editTousu;

    @Override
    protected int getLayout() {
        return R.layout.act_tousu;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("投诉建议");

        editTousu.setFilters(new InputFilter[]{new EditFilter(), new InputFilter.LengthFilter(500)});
        commit.setOnClickListener(this);
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onClick(View v) {
        String content = editTousu.getText().toString().trim();
        if (StringUtils.isEmpty(content)) {
            showToast("请输入投诉建议!");
            return;
        }
        if (content.length() < 10) {
            showToast("投诉建议长度需大于10位!");
            return;
        }
        mPresenter.tousu(content);
    }

    @Override
    public void sounrss() {
        showToast("投诉成功！");
    }
}
