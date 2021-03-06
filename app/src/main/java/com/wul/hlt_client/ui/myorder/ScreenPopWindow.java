package com.wul.hlt_client.ui.myorder;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wul.hlt_client.R;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

public class ScreenPopWindow extends PopupWindow {

    private RecyclerView recyclerView;
    private View window;

    public ScreenPopWindow(Activity context, int width, List<String> cityBOS) {
        super(context);
        LayoutInflater inflater = context.getLayoutInflater();
        window = inflater.inflate(R.layout.pop_recycle, null);
        recyclerView = window.findViewById(R.id.recycle);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        LGRecycleViewAdapter<String> adapter = new LGRecycleViewAdapter<String>(cityBOS) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_flow_child;
            }

            @Override
            public void convert(LGViewHolder holder, String cityBO, int position) {
                holder.setText(R.id.flow_child_text, cityBO);
            }
        };
        adapter.setOnItemClickListener(R.id.flow_child_text, (view, position) -> {
            if (ScreenPopWindow.this.onSelecte != null) {
                onSelecte.onClick(cityBOS.get(position), position);
            }
            dismiss();
        });
        recyclerView.setAdapter(adapter);
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.width = width - 6;
        recyclerView.setLayoutParams(params);
        this.setContentView(window);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        //设置宽高
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

        void onClick(String select, int position);

    }

}
