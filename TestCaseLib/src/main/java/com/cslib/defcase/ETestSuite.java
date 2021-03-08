package com.cslib.defcase;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 测试套件，组的概念，可以承载很多TestCase
 * @Version: 1.0
 * @Create: 2021/03/62 11:22:38
 * @author: sanbo
 */
public class ETestSuite {


    public ETestSuite() {
        this("");
    }
    public ETestSuite(String name) {
        if (TextUtils.isEmpty(name)) {
            this.mName = getClass().getName();
        } else {
            this.mName = name;
        }
    }

    public void addCase(ETestCase caze) {
        String caseName = caze.getName();
        if (!caseMap.containsKey(caseName)) {
            caseMap.put(caseName, caze);
        }
    }

    public String getName() {
        return mName;
    }

    public List<ETestCase> getAllCasesList() {
        CopyOnWriteArrayList<ETestCase> result = new CopyOnWriteArrayList<ETestCase>();
        if (caseMap.size() > 0) {
            Iterator<Map.Entry<String, ETestCase>> iterator = caseMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ETestCase> entry = iterator.next();
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public Map<String, ETestCase> getAllCasesMap() {
        return caseMap;
    }

    private String mName;
    private Map<String, ETestCase> caseMap = new HashMap<String, ETestCase>();
}
