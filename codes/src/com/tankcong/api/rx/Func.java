package com.tankcong.api.rx;

public interface Func<T, R> {
    R call(T t);
}
