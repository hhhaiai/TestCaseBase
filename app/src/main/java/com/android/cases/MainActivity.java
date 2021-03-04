package com.android.cases;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cslib.CaseHelper;
import com.cslib.cuscase.ECaseManager;
import com.cslib.defcase.ETestCase;
import com.cslib.defcase.ETestSuite;

import java.util.Set;


/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 主页
 * @Version: 1.0
 * @Create: 2021/03/62 11:16:38
 * @author: sanbo
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCases();
        CaseHelper.openCasePage(this);
    }

    private void addCases() {
        ETestSuite ts = new ETestSuite("测试分组1");
        for (int i = 0; i < 4; i++) {
            ts.addCase(new ETestCase1());
            ts.addCase(new ETestCase2());
        }
        CaseHelper.addSuite(ts);
        ETestSuite ts2 = new ETestSuite("测试分组2");
        for (int i = 0; i < 4; i++) {
            ts2.addCase(new ETestCase1());
            ts2.addCase(new ETestCase2());
        }
        CaseHelper.addSuite(ts2);


        ETestSuite tname = new ETestSuite("类名定义case");

        ECaseManager.getInstance().getCase(GoHomeCase.class);
        final Set<Class<?>> cases = ECaseManager.getInstance().getCases();
        for (final Class<?> aCase : cases) {
            tname.addCase(new ETestCase(aCase.getSimpleName()) {
                @Override
                public void prepare() {
                    ECaseManager.getInstance().getCase(aCase).prepare();
                }

                @Override
                public boolean predicate() {
                    return ECaseManager.getInstance().getCase(aCase).validate();
                }
            });
        }
        CaseHelper.addSuite(tname);
    }
}