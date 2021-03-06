package com.wul.hlt_client.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.LogUtils;
import com.contrarywind.view.WheelView;
import com.wul.hlt_client.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private TextView commit;
    private ImageView colse;

    private Calendar c;//

    private int currentYeay;   //当前年份
    private int currentMonth;    //当前月份

    private WheelView[] views;

    private String selectYear;    //选中的天数
    private String selectMonth;   //选中的月份
    private String selectDay;     //选中的日期
    private String selectStartHour;    //选中的开始时间
    private String selectEndHour;      //选中的结束时间

    private List<String> years;
    private List<String> months;
    private List<String> days;
    private List<String> hours;


    public TimeDialog(Activity context) {
        this.context = context;
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_time, null);

        c = Calendar.getInstance();
        currentYeay = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
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
        commit = view.findViewById(R.id.commit);
        colse = view.findViewById(R.id.colse);

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);

        TimePickerView builder = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

            }
        }).setDate(selectedDate)
                .setRangDate(startDate, endDate).setLayoutRes(R.layout.dialog_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {

                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();

        builder.show();

//        initView();
//        year.setAdapter(new ArrayWheelAdapter<>(getYear()));
//        month.setAdapter(new ArrayWheelAdapter<>(getMonth()));
//        day.setAdapter(new ArrayWheelAdapter<>(getDay(currentYeay, currentMonth)));
    }


    private void initView() {
        for (WheelView view : views) {
            view.setCyclic(false);
            view.setDividerColor(Color.parseColor("#ffffff"));
            view.setTextColorCenter(Color.parseColor("#383535"));
            view.setTextSize(13);
            view.setTextColorOut(Color.parseColor("#979797"));
            view.setGravity(Gravity.CENTER);
            view.setLineSpacingMultiplier(2);
        }
    }


    /**
     * 设置事件监听
     */
    private void setListener() {
        colse.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        year.setOnItemSelectedListener(index -> {
            LogUtils.i(index);
            month.setCurrentItem(0);
            day.setCurrentItem(0);
            day.setAdapter(new ArrayWheelAdapter<>(getDay(Integer.parseInt(years.get(index)),
                    1)));
        });
        month.setOnItemSelectedListener(index -> {
            LogUtils.i(index);
            day.setCurrentItem(0);
            day.setAdapter(new ArrayWheelAdapter<>(getDay(Integer.parseInt(years.get(year.getCurrentItem())),
                    1)));
        });
    }


    View.OnClickListener listener = v -> {
        switch (v.getId()) {
            case R.id.colse:
                dismiss();
                break;
            case R.id.commit:
                selectYear = years.get(year.getCurrentItem());
                selectMonth = months.get(month.getCurrentItem());
                selectDay = days.get(day.getCurrentItem());
                if (this.onSelectListener != null) {
                    this.onSelectListener.onCommit(selectYear + "/" + selectMonth + "/" + selectDay
                            + "   " + selectStartHour + "-" + selectEndHour);
                    this.onSelectListener.onSelect(selectYear, selectMonth, selectDay, selectStartHour, selectEndHour);
                    dismiss();
                } else {
                    dismiss();
                }
                break;
        }
    };


    /**
     * 获取年份
     */
    private List<String> getYear() {
        years = new ArrayList<>();
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        years.add(mYear + "");
        years.add(mYear + 1 + "");
        return years;
    }


    /**
     * 获取月份
     */
    private List<String> getMonth() {
        months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(i + "");
        }
        return months;
    }

    /**
     * 根据月份获取天数
     */
    private List<String> getDay(int year, int month) {
        days = new ArrayList<>();
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
        LogUtils.i(maxDate);
        return maxDate;
    }


    /**
     * 获取24小时集合
     */
    private List<String> getHours() {
        hours = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            hours.add(i + ":00");
        }
        return hours;
    }


    private onSelectListener onSelectListener;

    public void setOnSelectListener(onSelectListener listener) {
        this.onSelectListener = listener;
    }


    public interface onSelectListener {

        void onCommit(String date);

        void onSelect(String year, String month, String day, String startTime, String endTime);
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
