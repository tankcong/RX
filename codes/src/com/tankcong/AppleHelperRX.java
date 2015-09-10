package com.tankcong;

import com.tankcong.api.Apple;
import com.tankcong.api.Uri;
import com.tankcong.api.rx.ApiWrapperRX;
import rx.Observable;
import rx.functions.Func1;

import java.util.Collections;
import java.util.List;

public class AppleHelperRX {
    ApiWrapperRX apiWrapper;

    private static AppleHelperRX helper = null;

    private AppleHelperRX() {
        apiWrapper = new ApiWrapperRX();
    }
    public static AppleHelperRX getInstance() {
        if (helper == null) {
            helper = new AppleHelperRX();
        }
        return helper;
    }

    public Observable<Uri> saveTheBiggestApple(String query) {
        Observable<List<Apple>> appleListObservable = apiWrapper.queryApples("query");
        Observable<Apple> findBiggestObservable = appleListObservable.map(new Func1<List<Apple>, Apple>() {
            @Override
            public Apple call(List<Apple> apples) {
                return findBiggest(apples);
            }
        });
        Observable<Uri> storedUriObservable = findBiggestObservable.flatMap(new Func1<Apple, Observable<? extends Uri>>() {
            @Override
            public Observable<? extends Uri> call(Apple apple) {
                return apiWrapper.store(apple);
            }
        });
        return storedUriObservable;
    }

    public Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
