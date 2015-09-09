package com.tankcong;

import com.tankcong.cat.Api;
import com.tankcong.cat.ApiImpl;
import com.tankcong.cat.Cat;
import com.tankcong.cat.Uri;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    Api api;

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

    public Uri saveTheCuttestCat(String query) {
        try {
            List<Cat> cats = api.queryCats(query);
            Cat cuttestCat = findCuttest(cats);
            Uri savedUri = api.store(cuttestCat);
            return savedUri;
        } catch (Exception e) {
            return new Uri("empty");
        }
    }

    private Cat findCuttest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
