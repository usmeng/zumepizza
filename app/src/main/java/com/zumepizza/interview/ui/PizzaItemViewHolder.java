package com.zumepizza.interview.ui;

import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.zumepizza.interview.databinding.PizzaItemLayoutBinding;

import org.greenrobot.eventbus.EventBus;

public class PizzaItemViewHolder extends BaseViewHolder {

    public PizzaItemLayoutBinding binding;

    public PizzaItemViewHolder(PizzaItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    void bind(@NonNull ViewItemData viewItemData) {
        PizzaModel pizzaModel = (PizzaModel) viewItemData;
        binding.setViewmodel(pizzaModel);
        if (pizzaModel.assetImageURL != null && !pizzaModel.assetImageURL.isEmpty()) {
            Glide.with(binding.getRoot().getContext()).load(pizzaModel.assetImageURL).into(binding.pizzaImage);
        }
        binding.pizzaContainer.setOnClickListener(v -> EventBus.getDefault().post(new PizzaDetailEvent(pizzaModel)));
        binding.addToCartButton.setOnClickListener(v -> EventBus.getDefault().post(new AddPizzaToCartEvent()));
    }
}
