package com.android.cases;

import com.cslib.defcase.ETestCase;

public class ETestCase1 extends ETestCase {
    public ETestCase1() {
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
