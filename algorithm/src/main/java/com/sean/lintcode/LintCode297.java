package com.sean.lintcode;

import java.util.List;

public class LintCode297 {

    public static int maxNum(List<Integer> nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            ans = nums.get(i) > ans ? nums.get(i) : ans;
        }
        return ans;
    }

}
