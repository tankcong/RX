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

    public void saveTheBiggestApple(String query, Callback<Uri> callback) {
        apiWrapper.queryApples(query, new Callback<List<Apple>>() {
            @Override
            public void onResult(List<Apple> result) {
                Apple biggestApple = findBiggest(result);
                apiWrapper.store(biggestApple, callback);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });

    }

    private Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
