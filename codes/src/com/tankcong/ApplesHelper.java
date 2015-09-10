package com.tankcong;

import com.tankcong.api.*;

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
        AsyncJob<Apple> findBiggestJob = new AsyncJob<Apple>() {
            @Override
            public void start(Callback<Apple> callback) {
                queryListJob.start(new Callback<List<Apple>>() {
                    @Override
                    public void onResult(List<Apple> result) {
                        Apple apple = findBiggest(result);
                        callback.onResult(apple);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
        AsyncJob<Uri> storeJob = new AsyncJob<Uri>() {
            @Override
            public void start(Callback<Uri> callback) {
                findBiggestJob.start(new Callback<Apple>() {
                    @Override
                    public void onResult(Apple result) {
                        apiWrapper.store(result).start(callback);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
        return storeJob;
    }

    private Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
