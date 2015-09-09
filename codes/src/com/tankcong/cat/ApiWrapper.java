package com.tankcong.cat;

import java.util.List;

public class ApiWrapper {

    Api api;

    public ApiWrapper() {
        api = new ApiImpl();
    }

    public void queryCats(String query, Callback<List<Cat>> callback) {
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                callback.onResult(cats);
            }

            @Override
            public void onQueryFailed(Exception e) {
                callback.onError(e);
            }
        });
    }

    public void store(Cat cat, Callback<Uri> callback) {
        api.store(cat, new Api.StoreCallback() {
            @Override
            public void onCatStored(Uri uri) {
                callback.onResult(uri);
            }

            @Override
            public void onStoredFailed(Exception e) {
                callback.onError(e);
            }
        });
    }

}
