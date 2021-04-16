package me.hhhaiai.testcaselib.cuscase;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import me.hhhaiai.testcaselib.utils.L;

/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: 部分可定制的特殊case, 类名就是case名字
 * @Version: 1.0
 * @Create: 2021/03/62 11:40:19
 * @author: sanbo
 */
public class ECaseManager {
    public synchronized ECase getCase(Class<?> clazz) {
        ECase caze = caseMap.get(clazz);
        if (caze != null) {
            return caze;
        } else {
            try {
                caze = (ECase) clazz.newInstance();
                caseMap.put(clazz, caze);
            } catch (Throwable e) {
                L.e(e);
                throw new RuntimeException("can not get case !!", e);
            }
        }
        return caze;
    }

    public synchronized Set<Class<?>> getCases() {
        return caseMap.keySet();
    }

    /********************* get instance begin **************************/
    public static ECaseManager getInstance() {
        return HLODER.INSTANCE;
    }

    private static class HLODER {
        private static final ECaseManager INSTANCE = new ECaseManager();
    }

    private ECaseManager() {
    }

    /********************* get instance end **************************/
    private Map<Class<?>, ECase> caseMap = new ConcurrentHashMap<Class<?>, ECase>();

}
