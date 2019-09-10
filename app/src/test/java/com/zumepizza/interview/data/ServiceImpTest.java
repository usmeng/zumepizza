package com.zumepizza.interview.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ServiceImpTest {

    @Test
    public void fetchPizza() throws InterruptedException {
        ServiceImp.getInstance().fetchPizza().subscribe(new SingleObserver<List<CategoryServerModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Disposable ...");
                System.out.println(d.toString());
            }

            @Override
            public void onSuccess(List<CategoryServerModel> categoryServerModelList) {
                Assert.assertEquals(1, categoryServerModelList.size());

                CategoryServerModel categoryServerModel = categoryServerModelList.get(0);
                Assert.assertNotNull(categoryServerModel.chefsChoice);
                Assert.assertEquals(2, categoryServerModel.chefsChoice.size());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }
        });
        Thread.sleep(10000);
    }

}