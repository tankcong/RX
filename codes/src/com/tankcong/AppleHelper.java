package com.tankcong;

import com.tankcong.api.Api;
import com.tankcong.api.ApiImpl;
import com.tankcong.api.Apple;
import com.tankcong.api.Uri;

import java.util.Collections;
import java.util.List;

public class AppleHelper {

    Api api;

    interface BiggestAppleCallback {
        void onBiggestAppleSaved(Uri uri);
        void onError(Exception e);
    }

    private static AppleHelper helper = null;

    private AppleHelper() {
        api = new ApiImpl();
    }

    public static AppleHelper getInstance() {
        if(helper == null) {
            helper = new AppleHelper();
        }
        return helper;
    }

    public void saveTheBiggestApple(String query, BiggestAppleCallback callback) {
            api.queryApples(query, new Api.ApplesQueryCallback() {

                @Override
                public void onAppleListReceived(List<Apple> apples) {
                    Apple biggestApple = findBiggest(apples);
                    api.store(biggestApple, new Api.StoreCallback() {
                        @Override
                        public void onAppleStored(Uri uri) {
                            callback.onBiggestAppleSaved(uri);
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

    private Apple findBiggest(List<Apple> apples) {
        return Collections.max(apples);
    }
}
