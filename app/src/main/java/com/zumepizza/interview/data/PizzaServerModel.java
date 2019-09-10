package com.zumepizza.interview.data;

import java.util.List;

public class PizzaServerModel {
    public int id;
    public String name;
    public String price;
    public String menu_description;
    public ClassificationServerModel classificationServerModel;
    public AssertServerModel assets;
    public List<ToppingServerModel> toppings;
}
