package com.wul.hlt_client.ui.classify;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

/**
 * Created by wuliang on 2018/12/19.
 */

public class FlowAdapter extends LGRecycleViewAdapter<ClassifyBO> {

    private Context context;

    private int selectPosition = 0;

    public FlowAdapter(Context context, List<ClassifyBO> dataList) {
        super(dataList);
        this.context = context;
    }


    public void setSelectPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_flow;
    }

    @Override
    public void convert(LGViewHolder holder, ClassifyBO classifyBO, int position) {
        TextView flow = (TextView) holder.getView(R.id.flow_text);
        flow.setText(classifyBO.getCategoryName());
        if (position == selectPosition) {
            flow.setBackgroundResource(R.drawable.flow_bg);
            flow.setTextColor(Color.parseColor("#ffffff"));
        } else {
            flow.setBackgroundColor(Color.parseColor("#ffffff"));
            flow.setTextColor(Color.parseColor("#666666"));
        }
    }
}
