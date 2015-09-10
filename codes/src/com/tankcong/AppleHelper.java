package com.tankcong;

import com.tankcong.api.Api;
import com.tankcong.api.ApiImpl;
import com.tankcong.api.Apple;
import com.tankcong.api.Uri;

import java.util.Collections;
import java.util.List;

public class AppleHelper {

    Api api;

    private static AppleHelper helper = null;

    private AppleHelper() {
        api = new ApiImpl();
    }

    public static AppleHelper getInstance() {
        if(helper == null) {
            helper = new AppleHelper();
        }
        return helper;
    }

    public Uri saveTheBiggestApple(String query) {
        try {
            List<Apple> apples = api.queryApples(query);
            Apple biggestApple = findBiggest(apples);
            Uri savedUri = api.store(biggestApple);
            return savedUri;
        } catch (Exception e) {
            return new Uri("empty");
        }
    }

    private Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
