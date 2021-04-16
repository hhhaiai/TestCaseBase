package me.hhhaiai.testcaselib.cuscase;

import java.io.Serializable;

public interface ECase extends Serializable {
    void prepare();

    boolean validate(Object... args);
}
