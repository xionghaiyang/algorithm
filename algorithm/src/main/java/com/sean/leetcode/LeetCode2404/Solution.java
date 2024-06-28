package com.sean.leetcode.LeetCode2404;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-13 09:13
 * @Description: https://leetcode.cn/problems/most-frequent-even-element/
 * 2404. 出现最频繁的偶数元素
 * 给你一个整数数组 nums ，返回出现最频繁的偶数元素。
 * 如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。
 */
public class Solution {

    public int mostFrequentEven(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        int count = 0;
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();
            if (value > count) {
                count = value;
                res = key;
            } else if (value == count) {
                res = Math.min(res, key);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
