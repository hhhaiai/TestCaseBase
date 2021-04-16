package me.hhhaiai.testcaselib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

import me.hhhaiai.testcaselib.defcase.ETestCase;
import me.hhhaiai.testcaselib.defcase.ETestSuite;
import me.hhhaiai.testcaselib.page.ECaseHolder;
import me.hhhaiai.testcaselib.page.ListPage;
import me.hhhaiai.testcaselib.utils.TcaseClazzUtils;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/03/63 09:57:28
 * @author: sanbo
 */
public class CaseHelper {
    /**
     * 打开case页面
     *
     * @param context
     */
    public static void openCasePage(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, ListPage.class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 增加单组用例
     *
     * @param ctx
     * @param suiteName 分组名称
     * @param pkgPath   分组所在包路径
     */
    public static void addSuite(Context ctx, String suiteName, String pkgPath) {
        ETestSuite testSuite = new ETestSuite(suiteName);
        List<String> suiteClazz = TcaseClazzUtils.getClasseNameByPkgPath(ctx, pkgPath);
        for (String cls : suiteClazz) {
            // 支持Java 8+ Lambda 表达式
            if (!TextUtils.isEmpty(cls)&&!cls.contains("-$$Lambda$")){
                testSuite.addCase((ETestCase) TcaseClazzUtils.newInstance(cls));
            }
        }
        if (testSuite != null) {
            ECaseHolder.getInstance().addSuite(testSuite);
        }
    }

    @Deprecated
    public static void addSuite(ETestSuite suite) {
        if (suite != null) {
            ECaseHolder.getInstance().addSuite(suite);
        }
    }


    public static Context getCaseContext() {
        if (ListPage.isOn) {
            return ListPage.mContext;
        } else {
            return null;
        }
    }


}
