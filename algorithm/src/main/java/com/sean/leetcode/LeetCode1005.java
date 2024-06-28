package com.sean.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * K 次取反后最大化的数组和
 * https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
 */
public class LeetCode1005 {

    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = Arrays.stream(nums).sum();
        for (int i = -100; i < 0; i++) {
            if (map.containsKey(i)) {
                int ops = Math.min(k, map.get(i));
                res += (-i) * ops * 2;
                map.put(i, map.get(i) - ops);
                map.put(-i, map.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && k % 2 == 1 && !map.containsKey(0)) {
            for (int i = 1; i <= 100; i++) {
                if (map.containsKey(i)) {
                    res -= i * 2;
                    break;
                }
            }
        }
        return res;
    }

}
