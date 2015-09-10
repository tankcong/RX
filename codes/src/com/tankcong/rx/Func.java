package com.tankcong.rx;

public interface Func<T, R> {
    R call(T t);
}
