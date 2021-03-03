package com.cslib.defcase;


import com.cslib.utils.L;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 测试case基类
 * @Version: 1.0
 * @Create: 2021/03/62 10:57:43
 * @author: sanbo
 */
public abstract class ETestCase {


    protected String name;

    public ETestCase(String name) {
        this.name = name;
    }

    // 测试准备,可以设置响应环境.可不实现
    public abstract void prepare();

    //测试内容需要复写
    public abstract boolean predicate();

    // 测试实例，对外提供方法
    public boolean validate() {
        boolean validate = false;
        try {
            validate = predicate();
        } catch (Throwable e) {
            validate = false;
            L.e(e);

        }
        if (!validate) {
            L.e("[%s] 测试不通过. ", name);
        }
        return validate;
    }

    //case 名称
    public String getName() {
        return name;
    }

}
