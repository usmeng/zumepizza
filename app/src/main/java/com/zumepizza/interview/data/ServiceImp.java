package com.zumepizza.interview.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceImp {

    private static final String BASE_URL = "https://api.myjson.com";
    private Service service;
    private static ServiceImp instance = new ServiceImp();

    public static ServiceImp getInstance() {
        return instance;
    }

    private ServiceImp() {
        service = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service.class);
    }

    public Single<List<CategoryServerModel>> fetchPizza() {
        return service.fetchPizza().subscribeOn(Schedulers.io());
    }

    public void fetchPizza(@NonNull Service.Callback callback) {
        fetchPizza().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CategoryServerModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<CategoryServerModel> categoryServerModelList) {
                        callback.onSuccess(categoryServerModelList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }
                });
    }
}
