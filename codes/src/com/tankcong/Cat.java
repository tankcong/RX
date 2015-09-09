package com.tankcong;

import java.util.Random;

public class Cat implements Comparable<Cat>{

    private int cuteness;

    public Cat() {
        cuteness = new Random(System.currentTimeMillis()).nextInt(100);
    }

}
