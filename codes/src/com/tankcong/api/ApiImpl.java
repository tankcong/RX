package com.tankcong.api;

import java.util.ArrayList;
import java.util.List;

public class ApiImpl implements Api{

    @Override
    public List<Apple> queryApples(String query) {
        List<Apple> apples = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            apples.add(new Apple());
        }
        System.out.println(apples);
        return apples;
    }

    @Override
    public Uri store(Apple apple) {
        return new Uri("Apple: " + Integer.toString(apple.getWeight()));
    }
}
