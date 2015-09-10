package com.tankcong;

import com.tankcong.cat.ApiWrapper;
import com.tankcong.cat.Cat;
import com.tankcong.cat.Uri;
import com.tankcong.rx.ApiWrapperRX;
import rx.Observable;
import rx.functions.Func1;

import java.util.Collections;
import java.util.List;

public class CatsHelperRX {
    ApiWrapperRX apiWrapper;

    private static CatsHelperRX helper = null;

    private CatsHelperRX() {
        apiWrapper = new ApiWrapperRX();
    }
    public static CatsHelperRX getInstance() {
        if (helper == null) {
            helper = new CatsHelperRX();
        }
        return helper;
    }

    public Observable<Uri> saveTheCutestCat(String query) {
        Observable<List<Cat>> catsListObservable = apiWrapper.queryCats("query");
        Observable<Cat> findCutestObservable = catsListObservable.map(new Func1<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCuttest(cats);
            }
        });
        Observable<Uri> storedUriObservable = findCutestObservable.flatMap(new Func1<Cat, Observable<? extends Uri>>() {
            @Override
            public Observable<? extends Uri> call(Cat cat) {
                return apiWrapper.store(cat);
            }
        });
        return storedUriObservable;
    }

    public Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
