package com.tankcong.cat;

import java.util.Random;

public class Cat implements Comparable<Cat>{

    private int cuteness;

    private static Random rand = new Random(System.currentTimeMillis());

    public Cat() {
        cuteness = rand.nextInt(100);
    }

    public int getCuteness() {
        return this.cuteness;
    }

    @Override
    public int compareTo(Cat o) {
        return Integer.compare(this.cuteness, o.cuteness);
    }

    @Override
    public String toString() {
        return Integer.toString(cuteness);
    }
}
