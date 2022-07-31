package com.sean.lintcode;

import java.util.HashMap;

public class LintCode2400 {

    public static HashMap<Integer, String> createHashMap(int number, String str) {
        HashMap<Integer, String> ans = new HashMap<>();
        ans.put(number, str);
        return ans;
    }

}
