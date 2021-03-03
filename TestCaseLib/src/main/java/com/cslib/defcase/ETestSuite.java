package com.cslib.defcase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 测试套件，组的概念，可以承载很多TestCase
 * @Version: 1.0
 * @Create: 2021/03/62 11:22:38
 * @author: sanbo
 */
public class ETestSuite {
    private String name;
    private List<ETestCase> cases = new ArrayList<>();

    public ETestSuite(String name) {
        this.name = name;
    }

    public void addCase(ETestCase caze) {
        cases.add(caze);
    }

    public String getName() {
        return name;
    }

    public List<ETestCase> getAllCases() {
        return cases;
    }
}
