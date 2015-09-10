package com.tankcong;

import com.tankcong.api.Apple;
import com.tankcong.api.Uri;
import com.tankcong.api.rx.ApiWrapperRX;
import rx.Observable;
import rx.functions.Func1;

import java.util.Collections;
import java.util.List;

public class ApplesHelperRX {
    ApiWrapperRX apiWrapper;

    private static ApplesHelperRX helper = null;

    private ApplesHelperRX() {
        apiWrapper = new ApiWrapperRX();
    }
    public static ApplesHelperRX getInstance() {
        if (helper == null) {
            helper = new ApplesHelperRX();
        }
        return helper;
    }

    public Observable<Uri> saveTheBiggestApple(String query) {
        Observable<List<Apple>> applesListObservable = apiWrapper.queryApples("query");
        Observable<Apple> findBiggestObservable = applesListObservable.map(new Func1<List<Apple>, Apple>() {
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
