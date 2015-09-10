package com.tankcong;


import com.tankcong.api.Uri;

public class Main {

    public static void main(String[] args) {
        AppleHelper helper = AppleHelper.getInstance();
        Uri uri = helper.saveTheBiggestApple("query");
        System.out.println(uri);
    }
}
