package com.tankcong.api;

public abstract class AsyncJob<T> {

    public abstract void start(Callback<T> callback);

}
