package com.zumepizza.interview.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryServerModel {
    @SerializedName("Chef's Choice")
    public List<PizzaServerModel> chefsChoice;
    @SerializedName("Classics")
    public List<PizzaServerModel> classics;
    @SerializedName("Signature")
    public List<PizzaServerModel> signature;
    @SerializedName("Vegetarian")
    public List<PizzaServerModel> vegetarian;
}
