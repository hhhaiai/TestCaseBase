package me.hhhaiai.testcaselib.cuscase;

import android.app.Activity;
import android.content.Context;

import java.io.Serializable;

import me.hhhaiai.testcaselib.BuildConfig;
import me.hhhaiai.testcaselib.page.ListPage;
import me.hhhaiai.testcaselib.utils.EContext;

public interface ECase extends Serializable {
    void prepare();

    boolean validate(Object... args);

    public static Context getContext() {
        return EContext.getContext(ListPage.mContext);
    }

    public static Activity getActivity() {
        return ListPage.mContext;
    }

    public static String getTestCaseLibVersion() {
        return BuildConfig.TCL_VERSION;
    }
}
