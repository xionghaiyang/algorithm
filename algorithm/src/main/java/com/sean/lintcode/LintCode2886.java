package com.sean.lintcode;

import java.util.Map;
import java.util.TreeMap;

public class LintCode2886 {

    public static Map<String, Integer> sortMap(Map<String, Integer> map) {
        Map<String, Integer> res = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res.put(entry.getKey(), entry.getValue());
        }
        return res;
    }

}
