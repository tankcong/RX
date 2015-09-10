package com.tankcong.rx;

import com.tankcong.cat.*;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

public class ApiWrapperRX {

    Api api;

    public ApiWrapperRX() {
        api = new ApiImpl();
    }

    public Observable<List<Cat>> queryCats(String query) {
        return Observable.create(new Observable.OnSubscribe<List<Cat>>() {
            @Override
            public void call(Subscriber<? super List<Cat>> subscriber) {
                api.queryCats(query, new Api.CatsQueryCallback() {
                    @Override
                    public void onCatListReceived(List<Cat> cats) {
                        subscriber.onNext(cats);
                    }

                    @Override
                    public void onQueryFailed(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }

    public Observable<Uri> store(Cat cat) {
        return Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
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
