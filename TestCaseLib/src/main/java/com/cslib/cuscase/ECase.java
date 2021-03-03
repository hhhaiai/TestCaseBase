package com.cslib.cuscase;

public interface ECase {
    void prepare();

    boolean validate(Object... args);
}
