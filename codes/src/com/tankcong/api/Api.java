package com.tankcong.api;

import java.util.List;

public interface Api {

    interface ApplesQueryCallback {
        void onAppleListReceived(List<Apple> apples);
        void onQueryFailed(Exception e);
    }

    interface StoreCallback {
        void onAppleStored(Uri uri);
        void onStoredFailed(Exception e);
    }

    void queryApples(String query, ApplesQueryCallback callback);

    void store(Apple apple, StoreCallback callback);
}
