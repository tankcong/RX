package com.tankcong.api;

import java.util.List;

public interface Api {

    List<Apple> queryApples(String query);

    Uri store(Apple apple);
}
