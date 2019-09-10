package com.zumepizza.interview.ui;

public class PizzaDetailEvent {

    private PizzaModel pizzaModel;

    public PizzaDetailEvent(PizzaModel pizzaModel) {
        this.pizzaModel = pizzaModel;
    }

    public PizzaModel getPizzaModel() {
        return pizzaModel;
    }

}
