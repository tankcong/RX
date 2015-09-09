package com.tankcong;


import com.tankcong.cat.Uri;

public class Main {

    public static void main(String[] args) {
        CatsHelper helper = CatsHelper.getInstance();
        Uri uri = helper.saveTheCuttestCat("query");
        System.out.println(uri);
    }
}
