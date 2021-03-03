package com.cslib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.cslib.defcase.ETestSuite;
import com.cslib.page.ECaseHolder;
import com.cslib.page.ListPage;

public class CaseHelper {
    public static void openCasePage(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, ListPage.class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static void addSuite(ETestSuite suite) {
        if (suite != null) {
            ECaseHolder.getInstance().addSuite(suite);
        }
    }
}
