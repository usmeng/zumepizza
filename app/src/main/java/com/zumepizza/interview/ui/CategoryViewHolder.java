package com.zumepizza.interview.ui;


import com.zumepizza.interview.databinding.CategoryItemLayoutBinding;

public class CategoryViewHolder extends BaseViewHolder {

    public CategoryItemLayoutBinding binding;

    public CategoryViewHolder(CategoryItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    void bind(ViewItemData viewItemData) {
        binding.setData((PizzaCategory) viewItemData);
    }
}
