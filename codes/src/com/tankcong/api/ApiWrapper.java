package com.tankcong.api;

import java.util.List;

public class ApiWrapper {

    Api api;

    public ApiWrapper() {
        api = new ApiImpl();
    }

    public AsyncJob<List<Apple>> queryApples(String query) {
        return new AsyncJob<List<Apple>>() {
            @Override
            public void start(Callback<List<Apple>> callback) {
                api.queryApples(query, new Api.AppleQueryCallback() {
                    @Override
                    public void onAppleListReceived(List<Apple> apples) {
                        callback.onResult(apples);
                    }

                    @Override
                    public void onQueryFailed(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<Uri> store(Apple apple) {
        return new AsyncJob<Uri>() {
            @Override
            public void start(Callback<Uri> callback) {
                api.store(apple, new Api.StoreCallback() {
                    @Override
                    public void onAppleStored(Uri uri) {
                        callback.onResult(uri);
                    }

                    @Override
                    public void onStoredFailed(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }

}
