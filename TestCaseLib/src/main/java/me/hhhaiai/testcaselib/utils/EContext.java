package me.hhhaiai.testcaselib.utils;

import android.app.Application;
import android.content.Context;

public class EContext {
    private static Context mContext = null;

    public static Context getContext(Context context) {
        if (context != null) {
            mContext = context.getApplicationContext();
        }
        return getContextImpl();
    }

    public static Context getContext() {
        return getContextImpl();
    }

    private static Context getContextImpl() {
        try {
            if (mContext == null) {
                Object at =
                        TcInvoke.invokeStaticMethod(
                                "android.app.ActivityThread", "currentActivityThread");
                Application app = (Application) TcInvoke.invokeObjectMethod(at, "getApplication");
                if (app != null) {
                    mContext = app.getApplicationContext();
                }
                if (mContext == null) {
                    app =
                            (Application)
                                    TcInvoke.invokeStaticMethod(
                                            "android.app.AppGlobals", "getInitialApplication");
                    if (app != null) {
                        mContext = app.getApplicationContext();
                    }
                }
            }
        } catch (Throwable igone) {
            //            L.e(igone);
        }

        return mContext;
    }
}
