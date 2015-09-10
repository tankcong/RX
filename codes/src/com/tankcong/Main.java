package com.tankcong;


import com.tankcong.api.AsyncJob;
import com.tankcong.api.Callback;
import com.tankcong.api.Uri;
import rx.Observable;
import rx.functions.Action1;

public class Main {

    public static void main(String[] args) {
        startRX();
    }

    static void start() {
        AppleHelper helper = AppleHelper.getInstance();

        AsyncJob<Uri> storeJob = helper.saveTheBiggestApple("query");
        storeJob.start(new Callback<Uri>() {
            @Override
            public void onResult(Uri result) {
                System.out.println(result);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void startRX() {
        AppleHelperRX helper = AppleHelperRX.getInstance();
        Observable<Uri> storeObservable = helper.saveTheBiggestApple("query");
        storeObservable.subscribe(new Action1<Uri>() {
            @Override
            public void call(Uri uri) {
                System.out.println(uri);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    static void startRXLambda() {
        AppleHelperRX helper = AppleHelperRX.getInstance();
        Observable<Uri> storeObservable = helper.saveTheBiggestApple("query");
        storeObservable.subscribe(uri -> System.out.println(uri), throwable -> throwable.printStackTrace());
    }


}
