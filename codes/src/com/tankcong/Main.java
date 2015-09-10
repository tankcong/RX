package com.tankcong;


import com.tankcong.api.Uri;

public class Main {

    public static void main(String[] args) {
        AppleHelper helper = AppleHelper.getInstance();
        helper.saveTheBiggestApple("query", new AppleHelper.BiggestAppleCallback() {
            @Override
            public void onBiggestAppleSaved(Uri uri) {
                System.out.println(uri);
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e);
            }
        });
    }
}
