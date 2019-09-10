package com.zumepizza.interview.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zumepizza.interview.R;
import com.zumepizza.interview.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {

    private PizzaListFragment pizzaListFragment;
    private ActivityMainBinding binding;
    private int pizzaCountInCart = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setActionBar(binding.mainToolBar);
        binding.mainToolBarTitle.setText(getText(R.string.app_name));

        pizzaListFragment = (PizzaListFragment) getFragmentManager().findFragmentByTag(PizzaListFragment.TAG);
        if (pizzaListFragment == null) {
            pizzaListFragment = PizzaListFragment.newInstance();

            getFragmentManager().beginTransaction()
                    .add(R.id.main_fragment, pizzaListFragment, PizzaListFragment.TAG)
                    .commit();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPizzaDetailEvent(PizzaDetailEvent pizzaDetailEvent) {
        PizzaDetailFragment pizzaDetailFragment = PizzaDetailFragment.newInstance(pizzaDetailEvent.getPizzaModel());
        getFragmentManager().beginTransaction()
                .add(R.id.main_fragment, pizzaDetailFragment, PizzaDetailFragment.TAG)
                .addToBackStack("")
                .hide(pizzaListFragment)
                .commit();
    }

    @Subscribe
    public void onAddPizzaToCartEvent(AddPizzaToCartEvent addPizzaToCartEvent) {
        binding.actionBarCart.setText(String.valueOf(++pizzaCountInCart));
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
