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

    public AsyncJob<Uri> saveTheCuttestCat(String query) {
        AsyncJob<List<Cat>> queryListJob = apiWrapper.queryCats(query);
        AsyncJob<Cat> findCutestJob = new AsyncJob<Cat>() {
            @Override
            public void start(Callback<Cat> callback) {
                queryListJob.start(new Callback<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> result) {
                        Cat cat = findCuttest(result);
                        callback.onResult(cat);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
        AsyncJob<Uri> storeJob = new AsyncJob<Uri>() {
            @Override
            public void start(Callback<Uri> callback) {
                findCutestJob.start(new Callback<Cat>() {
                    @Override
                    public void onResult(Cat result) {
                        apiWrapper.store(result).start(callback);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
        return storeJob;
    }

    private Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
