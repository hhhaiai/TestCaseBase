package com.android.cases;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cslib.CaseHelper;
import com.cslib.defcase.ETestSuite;


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
    }
}