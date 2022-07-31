package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * https://leetcode-cn.com/problems/majority-element/
 */
public class LeetCode169 {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = nums.length;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > n / 2) {
                return num;
            }
            map.put(num, count);
        }
        return -1;
    }

}
