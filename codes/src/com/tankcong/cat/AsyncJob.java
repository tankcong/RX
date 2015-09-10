package com.tankcong.cat;

public abstract class AsyncJob<T> {

    public abstract void start(Callback<T> callback);

}
