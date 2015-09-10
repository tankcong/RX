package com.tankcong;

import com.tankcong.api.*;

import java.util.Collections;
import java.util.List;

public class AppleHelper {

    ApiWrapper apiWrapper;

    private static AppleHelper helper = null;

    private AppleHelper() {
        apiWrapper = new ApiWrapper();
    }

    public static AppleHelper getInstance() {
        if (helper == null) {
            helper = new AppleHelper();
        }
        return helper;
    }

    public AsyncJob<Uri> saveTheBiggestApple(String query) {

        AsyncJob<List<Apple>> queryListJob = apiWrapper.queryApples(query);

        AsyncJob<Apple> findBiggestJob = queryListJob.map(apples -> findBiggest(apples));

        AsyncJob<Uri> storeJob = findBiggestJob.flatMap(apple -> apiWrapper.store(apple));

       return storeJob;
    }

    public Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
