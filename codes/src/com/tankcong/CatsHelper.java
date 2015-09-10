package com.tankcong;

import com.tankcong.cat.*;
import com.tankcong.rx.Func;

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

        AsyncJob<Cat> findCutestJob = queryListJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCuttest(cats);
            }
        });

        AsyncJob<Uri> storeJob = findCutestJob.flatMap(new Func<Cat, AsyncJob<Uri>>() {
            @Override
            public AsyncJob<Uri> call(Cat cat) {
                return apiWrapper.store(cat);
            }
        });
       return storeJob;
    }

    private Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
