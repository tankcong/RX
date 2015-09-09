package com.tankcong.cat;

import java.util.List;

public interface Api {

    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);
        void onQueryFailed(Exception e);
    }

    interface StoreCallback {
        void onCatStored(Uri uri);
        void onStoredFailed(Exception e);
    }

    void queryCats(String query, CatsQueryCallback callback);

    void store(Cat cat, StoreCallback callback);
}
