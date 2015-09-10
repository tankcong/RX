package com.tankcong.api;

public interface Func<T, R> {
    R call(T t);
}
