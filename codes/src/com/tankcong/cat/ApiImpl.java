package com.tankcong.cat;

import java.util.ArrayList;
import java.util.List;

public class ApiImpl implements Api {

    @Override
    public void queryCats(String query, CatsQueryCallback callback) {
        // should be async
        try {
            List<Cat> cats = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                cats.add(new Cat());
            }
            System.out.println(cats);
            // sync
            callback.onCatListReceived(cats);
        } catch (Exception e) {
            callback.onQueryFailed(e);
        }
    }

    @Override
    public void store(Cat cat, StoreCallback callback) {
        // should be async
        Uri uri = new Uri(cat.toString());
        // sync
        callback.onCatStored(uri);
    }
}
