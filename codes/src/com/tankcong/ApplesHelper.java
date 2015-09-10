package com.tankcong;

import com.tankcong.api.*;
import com.tankcong.api.rx.Func;

import java.util.Collections;
import java.util.List;

public class ApplesHelper {

    ApiWrapper apiWrapper;

    private static ApplesHelper helper = null;

    private ApplesHelper() {
        apiWrapper = new ApiWrapper();
    }

    public static ApplesHelper getInstance() {
        if (helper == null) {
            helper = new ApplesHelper();
        }
        return helper;
    }

    public AsyncJob<Uri> saveTheBiggestApple(String query) {
        AsyncJob<List<Apple>> queryListJob = apiWrapper.queryApples(query);

        AsyncJob<Apple> findBiggestJob = queryListJob.map(new Func<List<Apple>, Apple>() {
            @Override
            public Apple call(List<Apple> apples) {
                return findBiggest(apples);
            }
        });

        AsyncJob<Uri> storeJob = findBiggestJob.flatMap(new Func<Apple, AsyncJob<Uri>>() {
            @Override
            public AsyncJob<Uri> call(Apple apple) {
                return apiWrapper.store(apple);
            }
        });
       return storeJob;
    }

    private Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
