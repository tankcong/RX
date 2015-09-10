package com.tankcong.api;

import java.util.List;

public class ApiWrapper {

    Api api;

    public ApiWrapper() {
        api = new ApiImpl();
    }

    public void queryApples(String query, Callback<List<Apple>> callback) {
        api.queryApples(query, new Api.ApplesQueryCallback() {
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

    public void store(Apple apple, Callback<Uri> callback) {
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

}
