package me.hhhaiai.testcaselib.page;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import me.hhhaiai.testcaselib.defcase.ETestCase;
import me.hhhaiai.testcaselib.defcase.ETestSuite;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 测试case持有者
 * @Version: 1.0
 * @Create: 2021/03/62 15:26:57
 * @author: sanbo
 */
public class ECaseHolder {


    public List<ETestSuite> getAllSuites() {
        CopyOnWriteArrayList<ETestSuite> result = new CopyOnWriteArrayList<ETestSuite>();
        if (kvs.size() > 0) {
            Iterator<Map.Entry<String, ETestSuite>> iterator = kvs.entrySet().iterator();
            while (iterator.hasNext()) {
                result.add(iterator.next().getValue());
            }
        }
        return result;
    }


    public void addSuite(ETestSuite suite) {
        String suiteName = suite.getName();
        if (kvs.containsKey(suiteName)) {
            ETestSuite memorySuite = kvs.get(suiteName);
            Map<String, ETestCase> newCase = suite.getAllCasesMap();
            //compare and marge
            if (newCase.size() > 0) {
                Iterator<Map.Entry<String, ETestCase>> newIterator = newCase.entrySet().iterator();
                ETestCase value = null;
                while (newIterator.hasNext()) {
                    value = null;
                    // 新加case无法覆盖老case
                    value = newIterator.next().getValue();
                    memorySuite.addCase(value);
                }
            }
            kvs.remove(suiteName);
            kvs.put(suiteName, memorySuite);
        } else {
            kvs.put(suiteName, suite);
        }
    }

    /********************* get instance begin **************************/
    public static ECaseHolder getInstance() {
        return HLODER.INSTANCE;
    }

    private static class HLODER {
        private static final ECaseHolder INSTANCE = new ECaseHolder();
    }

    private ECaseHolder() {
    }

    /********************* get instance end **************************/

    private Map<String, ETestSuite> kvs = new HashMap<String, ETestSuite>();

}