package com.cslib.page;


import com.cslib.defcase.ETestSuite;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 测试case持有者
 * @Version: 1.0
 * @Create: 2021/03/62 15:26:57
 * @author: sanbo
 */
public class ECaseHolder {


    public List<ETestSuite> getAllSuites() {
        return suites;
    }

    public void addSuite(ETestSuite suite) {
        suites.add(suite);
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
    private List<ETestSuite> suites = new CopyOnWriteArrayList<>();
}