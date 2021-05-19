package me.hhhaiai.testcaselib.defcase;


import android.content.Context;
import android.text.TextUtils;

import java.io.Serializable;

import me.hhhaiai.testcaselib.page.ListPage;
import me.hhhaiai.testcaselib.utils.EContext;
import me.hhhaiai.testcaselib.utils.L;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 测试case基类
 * @Version: 1.0
 * @Create: 2021/03/62 10:57:43
 * @author: sanbo
 */
public abstract class ETestCase implements Serializable {

    public ETestCase() {
        this("");
    }

    public ETestCase(String name) {
        if (TextUtils.isEmpty(name)) {
            this.mName = getClass().getName();
        } else {
            this.mName = name;
        }
    }

    // 测试准备,可以设置响应环境.可不实现
    public abstract void prepare();

    //测试内容需要复写
    public abstract boolean predicate();

    public Context getContext() {
        return EContext.getContext(ListPage.mContext);
    }

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
            L.e("[%s] 测试不通过. ", mName);
        }
        return validate;
    }

    //case 名称
    public String getName() {
        return mName;
    }

    protected String mName;
}
