package com.tankcong;


import com.tankcong.cat.Uri;

public class Main {

    public static void main(String[] args) {
        CatsHelper helper = CatsHelper.getInstance();
        helper.saveTheCuttestCat("query", new CatsHelper.CuttestCatCallback() {
            @Override
            public void onCuttestCatSaved(Uri uri) {
                System.out.println(uri);
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e);
            }
        });
    }
}
