package com.android.cases;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.android.cases.case1.ETestCase1;
import com.android.cases.case1.ETestCase2;
import com.android.cases.case1.ETestCase3;
import com.android.cases.case2.GoHomeCase;
import com.cslib.CaseHelper;
import com.cslib.cuscase.ECaseManager;
import com.cslib.defcase.ETestCase;
import com.cslib.defcase.ETestSuite;
import com.cslib.utils.L;

import java.util.Set;


/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 主页
 * @Version: 1.0
 * @Create: 2021/03/62 11:16:38
 * @author: sanbo
 */
public class MainActivity extends Activity {

    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        addCases();
    }

    private void addCases() {
        for (int i = 1; i < 10; i++) {
//            ETestSuite ts = new ETestSuite("测试分组1");
//            ts.addCase(new ETestCase1());
//            ts.addCase(new ETestCase2());
//            ts.addCase(new ETestCase3());
//            CaseHelper.addSuite(ts);
//            ETestSuite ts2 = new ETestSuite("测试分组2");
//            ts2.addCase(new ETestCase1());
//            ts2.addCase(new ETestCase2());
//            ts2.addCase(new ETestCase3());
//            CaseHelper.addSuite(ts2);
//            ETestSuite ts3 = new ETestSuite();
//            ts3.addCase(new ETestCase1());
//            ts3.addCase(new ETestCase2());
//            ts3.addCase(new ETestCase3());
//            CaseHelper.addSuite(ts3);
            if (i % 2 == 0) {
                CaseHelper.addSuite(mContext, "测试分组" + i, "com.android.cases.case1");
            } else {
                CaseHelper.addSuite(mContext, "测试分组" + i, "com.android.cases.case3");
            }

        }


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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGG:
                Context c = CaseHelper.getCaseContext();
                L.i("context: " + c);
                CaseHelper.openCasePage(this);
                break;
            default:
                break;
        }
    }
}