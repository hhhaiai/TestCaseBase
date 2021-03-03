package com.android.cases;


import com.cslib.defcase.ETestCase;

public class ETestCase2 extends ETestCase {
    public ETestCase2() {
        super("TestCase2");
    }

    @Override
    public void prepare() {
    }

    @Override
    public boolean predicate() {
        return true;
    }

}
