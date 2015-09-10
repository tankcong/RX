package com.tankcong.api.rx;

import com.tankcong.api.*;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

public class ApiWrapperRX {

    Api api;

    public ApiWrapperRX() {
        api = new ApiImpl();
    }

    public Observable<List<Apple>> queryApples(String query) {
        return Observable.create(new Observable.OnSubscribe<List<Apple>>() {
            @Override
            public void call(Subscriber<? super List<Apple>> subscriber) {
                api.queryApples(query, new Api.ApplesQueryCallback() {
                    @Override
                    public void onAppleListReceived(List<Apple> apples) {
                        subscriber.onNext(apples);
                    }

                    @Override
                    public void onQueryFailed(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }

    public Observable<Uri> store(Apple apple) {
        return Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
                api.store(apple, new Api.StoreCallback() {
                    @Override
                    public void onAppleStored(Uri uri) {
                        subscriber.onNext(uri);
                    }

                    @Override
                    public void onStoredFailed(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }

}
