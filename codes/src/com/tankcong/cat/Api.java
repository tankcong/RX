package com.tankcong.cat;

import java.util.List;

public interface Api {

    List<Cat> queryCats(String cats);

    Uri store(Cat cat);
}
