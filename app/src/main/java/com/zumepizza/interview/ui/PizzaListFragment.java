package com.zumepizza.interview.ui;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zumepizza.interview.R;
import com.zumepizza.interview.data.CategoryServerModel;
import com.zumepizza.interview.data.Service;
import com.zumepizza.interview.data.ServiceImp;
import com.zumepizza.interview.databinding.PizzaListFragmentBinding;
import com.zumepizza.interview.transformer.PizzaTransformer;

import java.util.List;

public class PizzaListFragment extends Fragment {
    public static final String TAG = PizzaListFragment.class.getSimpleName();
    private PizzaListFragmentBinding binding;
    private PizzaTransformer pizzaTransformer;

    public static PizzaListFragment newInstance() {
        return new PizzaListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pizzaTransformer = new PizzaTransformer();
        loadData();
    }

    private void loadData() {
        ServiceImp.getInstance().fetchPizza(new Service.Callback() {
            @Override
            public void onSuccess(List<CategoryServerModel> categoryServerModelList) {
                binding.pizzaRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));

                List<ViewItemData> dataList = pizzaTransformer.toPizzaList(categoryServerModelList);
                binding.pizzaRecycleview.setAdapter(new PizzaListAdapter(dataList));
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.pizza_list_fragment, container, false);
        return binding.getRoot();
    }
}
