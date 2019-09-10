package com.zumepizza.interview.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Service {

    @GET("bins/snyji")
    Single<List<CategoryServerModel>> fetchPizza();

    interface Callback {

        void onSuccess(List<CategoryServerModel> categoryServerModelList);
        void onError(Throwable throwable);
    }
}
