package com.tankcong.cat;

public interface Func<T, R> {
    R call(T t);
}
