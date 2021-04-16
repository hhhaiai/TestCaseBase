package com.android.cases.case2;


import me.hhhaiai.testcaselib.defcase.ETestCase;

public class TestCase2 extends ETestCase {
    public TestCase2() {
        super("TestCase1");
    }

    @Override
    public void prepare() {
    }

    @Override
    public boolean predicate() {
        return false;
    }

}
