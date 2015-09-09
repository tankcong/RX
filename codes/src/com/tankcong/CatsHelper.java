package com.tankcong;

import com.tankcong.cat.Api;
import com.tankcong.cat.ApiImpl;
import com.tankcong.cat.Cat;
import com.tankcong.cat.Uri;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    Api api;

    interface CuttestCatCallback {
        void onCuttestCatSaved(Uri uri);
        void onError(Exception e);
    }

    private static CatsHelper helper = null;

    private CatsHelper() {
        api = new ApiImpl();
    }

    public static CatsHelper getInstance() {
        if(helper == null) {
            helper = new CatsHelper();
        }
        return helper;
    }

    public void saveTheCuttestCat(String query, CuttestCatCallback callback) {
            api.queryCats(query, new Api.CatsQueryCallback() {

                @Override
                public void onCatListReceived(List<Cat> cats) {
                    Cat cuttestCat = findCuttest(cats);
                    api.store(cuttestCat, new Api.StoreCallback() {
                        @Override
                        public void onCatStored(Uri uri) {
                            callback.onCuttestCatSaved(uri);
                        }

                        @Override
                        public void onStoredFailed(Exception e) {
                            callback.onError(e);
                        }
                    });
                }

                @Override
                public void onQueryFailed(Exception e) {
                    callback.onError(e);
                }
            });
    }

    private Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
