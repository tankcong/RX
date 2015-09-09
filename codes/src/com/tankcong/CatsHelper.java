package com.tankcong;

import com.tankcong.cat.*;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    ApiWrapper apiWrapper;

    private static CatsHelper helper = null;

    private CatsHelper() {
        apiWrapper = new ApiWrapper();
    }

    public static CatsHelper getInstance() {
        if (helper == null) {
            helper = new CatsHelper();
        }
        return helper;
    }

    public void saveTheCuttestCat(String query, Callback<Uri> callback) {
        apiWrapper.queryCats(query, new Callback<List<Cat>>() {
            @Override
            public void onResult(List<Cat> result) {
                Cat cutestCat = findCuttest(result);
                apiWrapper.store(cutestCat, callback);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });

    }

    private Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
