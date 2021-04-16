package com.cslib.cuscase;

import java.io.Serializable;

public interface ECase extends Serializable {
    void prepare();

    boolean validate(Object... args);
}
