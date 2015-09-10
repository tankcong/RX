package com.tankcong.api;

import java.util.ArrayList;
import java.util.List;

public class ApiImpl implements Api {

    @Override
    public void queryApples(String query, ApplesQueryCallback callback) {
        // should be async
        try {
            List<Apple> apples = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                apples.add(new Apple());
            }
            System.out.println(apples);
            // sync
            callback.onAppleListReceived(apples);
        } catch (Exception e) {
            callback.onQueryFailed(e);
        }
    }

    @Override
    public void store(Apple apple, StoreCallback callback) {
        // should be async
        try {
            Uri uri = new Uri(apple.toString());
            callback.onAppleStored(uri);
        } catch (Exception e) {
            // sync
            callback.onStoredFailed(e);
        }
    }
}
