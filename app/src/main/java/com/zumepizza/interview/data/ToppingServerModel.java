package com.zumepizza.interview.data;

public class ToppingServerModel {

    public int id;
    public String name;
    public String description;
    public Asset asset;

    public class Asset {
        public String url;
    }
}
