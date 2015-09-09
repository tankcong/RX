package com.tankcong.cat;

import java.util.ArrayList;
import java.util.List;

public class ApiImpl implements Api{

    @Override
    public List<Cat> queryCats(String query) {
        List<Cat> cats = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            cats.add(new Cat());
        }
        System.out.println(cats);
        return cats;
    }

    @Override
    public Uri store(Cat cat) {
        return new Uri("Cat: " + Integer.toString(cat.getCuteness()));
    }
}
