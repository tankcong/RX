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

        AsyncJob<Cat> findCutestJob = queryListJob.map(cats -> findCuttest(cats));

        AsyncJob<Uri> storeJob = findCutestJob.flatMap(cat -> apiWrapper.store(cat));

       return storeJob;
    }

    public Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
