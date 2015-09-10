package com.tankcong;


import com.tankcong.cat.AsyncJob;
import com.tankcong.cat.Callback;
import com.tankcong.cat.Uri;

public class Main {

    public static void main(String[] args) {


    }

    void start() {
        CatsHelper helper = CatsHelper.getInstance();

        AsyncJob<Uri> storeJob = helper.saveTheCuttestCat("query");
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

    void startRX() {
        CatsHelperRX helper = CatsHelperRX.getInstance();
    }

}
