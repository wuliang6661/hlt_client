package com.wul.hlt_client.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.wul.hlt_client.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wuliang on 2018/12/26.
 * <p>
 * 时间选择器的弹窗
 */

public class TimeDialog extends PopupWindow {

    private Activity context;
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private WheelView startHour;
    private WheelView endHour;
    private TextView commit;
    private ImageView colse;

    Calendar c;//

    private int currentYeay;   //当前年份
    private int currentMonth;    //当前月份
    private int currenDay;   // 当前天数

    public TimeDialog(Activity context) {
        this.context = context;
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_time, null);

        c = Calendar.getInstance();
        currentYeay = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        currenDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
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
     * 初始化布局
     */
    private void initvition(View view) {
        year = view.findViewById(R.id.year);
        month = view.findViewById(R.id.month);
        day = view.findViewById(R.id.day);
        startHour = view.findViewById(R.id.hour);
        endHour = view.findViewById(R.id.end_hour);
        commit = view.findViewById(R.id.commit);
        colse = view.findViewById(R.id.colse);

        year.setCyclic(false);
        month.setCyclic(false);
        day.setCyclic(false);
        startHour.setCyclic(false);
        endHour.setCyclic(false);
        year.setAdapter(new ArrayWheelAdapter<>(getYear()));
        month.setAdapter(new ArrayWheelAdapter<>(getMonth()));
        day.setAdapter(new ArrayWheelAdapter<>(getDay(currentYeay, currentMonth)));
        startHour.setAdapter(new ArrayWheelAdapter<>(getHours()));
        endHour.setAdapter(new ArrayWheelAdapter<>(getHours()));
    }


    /**
     * 设置事件监听
     */
    private void setListener() {

    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.colse:
                    dismiss();
                    break;
                case R.id.commit:

                    break;
            }
        }
    };


    /**
     * 获取年份
     */
    private List<String> getYear() {
        List<String> years = new ArrayList<>();
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        years.add(mYear + "");
        years.add(mYear + 1 + "");
        return years;
    }


    /**
     * 获取月份
     */
    private List<String> getMonth() {
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(i + "");
        }
        return months;
    }

    /**
     * 根据月份获取天数
     */
    private List<String> getDay(int year, int month) {
        List<String> days = new ArrayList<>();
        int dayNum = getDaysByYearMonth(year, month);
        for (int i = 1; i <= dayNum; i++) {
            days.add(i + "");
        }
        return days;
    }


    /**
     * 根据 年、月 获取对应的月份 的 天数
     */
    private int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 获取24小时集合
     */
    private List<String> getHours() {
        List<String> hours = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            hours.add(i + ":00");
        }
        return hours;
    }


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
}
