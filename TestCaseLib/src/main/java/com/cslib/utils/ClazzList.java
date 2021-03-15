package com.cslib.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 获取包名下的所有类
 * @Version: 1.0
 * @Create: 2021/03/74 15:48:43
 * @author: sanbo
 */
public class ClazzList {

    public static List<String> getClasseNameByPkgPath(Context context, String pkgPath) {
        ArrayList<String> classes = new ArrayList<String>();
        try {
            String packageCodePath = context.getPackageCodePath();
            DexFile df = new DexFile(packageCodePath);
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
                String className = iter.nextElement();
                if (className.startsWith(pkgPath)) {
                    classes.add(className);
                }
            }
        } catch (Throwable e) {
            L.e(e);
        }
        return classes;
    }
}
