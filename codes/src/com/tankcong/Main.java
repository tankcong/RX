package com.tankcong;


import com.tankcong.api.AsyncJob;
import com.tankcong.api.Callback;
import com.tankcong.api.Uri;

public class Main {

    public static void main(String[] args) {

        ApplesHelper helper = ApplesHelper.getInstance();

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
}
