package com.wul.hlt_client.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wul.hlt_client.R;

/**
 * Created by wuliang on 2018/12/24.
 */

public class PayDialog extends PopupWindow {

    private Activity context;
    private CheckBox aliPay;
    private CheckBox huodao;
    private TextView commit;
    private LinearLayout aliPayLayout;
    private LinearLayout huodaoPayLayout;
    private ImageView colse;
    private int type;


    public PayDialog(Activity context, int type) {
        this.context = context;
        this.type = type;
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_pay, null);
        initvition(dialogView);
        setListener();
        this.setBackgroundDrawable(new ColorDrawable(0));
        this.setContentView(dialogView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.anim_menu_bottombar);
        //实例化一个ColorDrawable颜色为半透明
        // ColorDrawable dw = new ColorDrawable(0x808080);
        //设置SelectPicPopupWindow弹出窗体的背景
        // this.setBackgroundDrawable(dw);
        this.setOnDismissListener(() -> backgroundAlpha(1f));
    }


    /**
     * 初始化弹窗控件
     */
    private void initvition(View mView) {
        aliPay = mView.findViewById(R.id.ali_checkbox);
        huodao = mView.findViewById(R.id.checkbox);
        commit = mView.findViewById(R.id.commit);
        aliPayLayout = mView.findViewById(R.id.ali_pay);
        huodaoPayLayout = mView.findViewById(R.id.huodao_pay);
        colse = mView.findViewById(R.id.colse);
        switch (type) {
            case 1:
                aliPay.setChecked(true);
                huodao.setChecked(false);
                break;
            case 2:
                aliPay.setChecked(false);
                huodao.setChecked(true);
                break;
        }
    }


    /**
     * 设置监听
     */
    private void setListener() {
        commit.setOnClickListener(listener);
        aliPayLayout.setOnClickListener(listener);
        huodaoPayLayout.setOnClickListener(listener);
        colse.setOnClickListener(listener);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.commit:
                    if (onCommitListener != null) {
                        if (aliPay.isChecked()) {
                            onCommitListener.onCommit(1);
                        } else {
                            onCommitListener.onCommit(2);
                        }
                    }
                    dismiss();
                    break;
                case R.id.ali_pay:
                    aliPay.setChecked(true);
                    huodao.setChecked(false);
                    break;
                case R.id.huodao_pay:
                    aliPay.setChecked(false);
                    huodao.setChecked(true);
                    break;
                case R.id.colse:
                    dismiss();
                    break;
            }
        }
    };


    /***
     * 显示时将屏幕置为透明
     */
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        backgroundAlpha(0.5f);
    }


    /**
     * 设置添加屏幕的背景透明度
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    private onCommitListener onCommitListener;

    public void setOnComitListener(onCommitListener listener) {
        this.onCommitListener = listener;
    }


    public interface onCommitListener {

        void onCommit(int type);
    }

}
