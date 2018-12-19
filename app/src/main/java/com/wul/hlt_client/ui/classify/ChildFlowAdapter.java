package com.wul.hlt_client.ui.classify;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

/**
 * Created by wuliang on 2018/12/19.
 */

public class ChildFlowAdapter extends LGRecycleViewAdapter<ClassifyBO> {

    private Context context;

    private int selectPosition = 0;

    public ChildFlowAdapter(Context context, List<ClassifyBO> dataList) {
        super(dataList);
        this.context = context;
    }


    public void setSelectPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_flow_child;
    }

    @Override
    public void convert(LGViewHolder holder, ClassifyBO classifyBO, int position) {
        TextView flow = (TextView) holder.getView(R.id.flow_child_text);
        flow.setText(classifyBO.getSubCategoryName());
        TextPaint paint = flow.getPaint();
        if (position == selectPosition) {
            flow.setTextColor(Color.parseColor("#333333"));
            paint.setFakeBoldText(true);
        } else {
            flow.setTextColor(Color.parseColor("#666666"));
            paint.setFakeBoldText(false);
        }
    }
}
