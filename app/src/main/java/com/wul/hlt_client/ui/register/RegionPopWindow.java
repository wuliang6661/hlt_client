package com.wul.hlt_client.ui.register;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.entity.CityRegionBO;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

/**
 * Created by wuliang on 2018/12/20.
 */

public class RegionPopWindow extends PopupWindow {

    private RecyclerView recyclerView;
    private View window;

    public RegionPopWindow(Activity context, List<CityRegionBO> cityBOS) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        window = inflater.inflate(R.layout.pop_recycle, null);
        recyclerView = window.findViewById(R.id.recycle);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        LGRecycleViewAdapter<CityRegionBO> adapter = new LGRecycleViewAdapter<CityRegionBO>(cityBOS) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_flow_child;
            }

            @Override
            public void convert(LGViewHolder holder, CityRegionBO cityBO, int position) {
                holder.setText(R.id.flow_child_text, cityBO.getRegionName());
            }
        };
        adapter.setOnItemClickListener(R.id.flow_child_text, (view, position) -> {
            if (RegionPopWindow.this.onSelecte != null) {
                onSelecte.onClick(cityBOS.get(position));
            }
        });
        recyclerView.setAdapter(adapter);
        this.setContentView(window);
        this.setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        window.setOnTouchListener((v, event) -> {
            int height = window.findViewById(R.id.pop_layout).getTop();
            int y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });
    }


    private onSelecte onSelecte;

    public void setOnSelecte(onSelecte selecte) {
        this.onSelecte = selecte;
    }


    public interface onSelecte {

        void onClick(CityRegionBO cityBO);

    }
}
