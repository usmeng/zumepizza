package com.zumepizza.interview.ui;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zumepizza.interview.R;
import com.zumepizza.interview.databinding.PizzaDetailLayoutBinding;

import org.greenrobot.eventbus.EventBus;

public class PizzaDetailFragment extends Fragment {

    public static final String TAG = PizzaDetailFragment.class.getSimpleName();
    private static final String PIZZA_MODEL_KEY = "pizza_model";
    private PizzaModel pizzaModel;

    public static PizzaDetailFragment newInstance(PizzaModel pizzaModel) {
        PizzaDetailFragment fragment = new PizzaDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PIZZA_MODEL_KEY, pizzaModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pizzaModel = (PizzaModel) getArguments().getSerializable(PIZZA_MODEL_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        PizzaDetailLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.pizza_detail_layout, container, false);
        binding.setViewmodel(pizzaModel);
        if (pizzaModel.assetImageURL != null && !pizzaModel.assetImageURL.isEmpty()) {
            Glide.with(binding.getRoot().getContext()).load(pizzaModel.assetImageURL).into(binding.pizzaImage);
        }
        binding.addToCartButton.setOnClickListener(v -> EventBus.getDefault().post(new AddPizzaToCartEvent()));
        return binding.getRoot();
    }
}
