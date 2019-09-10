package com.zumepizza.interview.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zumepizza.interview.R;

import java.util.List;

public class PizzaListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<ViewItemData> dataList;
    private static final int CATEGORY_TYPE = 0;
    private static final int PIZZA_TYPE = 1;

    public PizzaListAdapter(List<ViewItemData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CATEGORY_TYPE) {
            return new CategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_item_layout, null, false));
        }
        return new PizzaItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pizza_item_layout, null, false));
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ViewItemData viewItemData = dataList.get(position);
        holder.bind(viewItemData);
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position) instanceof PizzaCategory ? CATEGORY_TYPE : PIZZA_TYPE;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
