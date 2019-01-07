package com.wul.hlt_client.ui.myorder;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

public class RecycleAdapter extends LGRecycleViewAdapter<OrderDayBo.AddressMyOrderListBean> {


    public RecycleAdapter(List<OrderDayBo.AddressMyOrderListBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_order_lishi;
    }

    @Override
    public void convert(LGViewHolder holder, OrderDayBo.AddressMyOrderListBean addressMyOrderListBean,
                        int position) {


    }
}
