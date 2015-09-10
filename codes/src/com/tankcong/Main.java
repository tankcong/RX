package com.tankcong;


import com.tankcong.api.Callback;
import com.tankcong.api.Uri;

public class Main {

    public static void main(String[] args) {

        ApplesHelper helper = ApplesHelper.getInstance();

        helper.saveTheBiggestApple("query", new Callback<Uri>() {
            @Override
            public void onResult(Uri uri) {
                System.out.println(uri);
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e);
            }
        });
    }
}
