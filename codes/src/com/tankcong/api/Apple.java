package com.tankcong.api;

import java.util.Random;

public class Apple implements Comparable<Apple>{

    private int weight;

    private static Random rand = new Random(System.currentTimeMillis());

    public Apple() {
        weight = rand.nextInt(100);
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Apple o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return Integer.toString(weight);
    }
}
