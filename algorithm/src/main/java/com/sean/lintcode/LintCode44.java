package com.sean.lintcode;

import java.util.List;

public class LintCode44 {

    public static int minSubArray(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            ans = Math.min(ans, sum - maxSum);
            maxSum = Math.max(maxSum, sum);
        }
        return ans;
    }

}
