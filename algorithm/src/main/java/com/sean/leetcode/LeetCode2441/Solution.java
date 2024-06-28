package com.sean.leetcode.LeetCode2441;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-15 08:42
 * @Description: https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative/
 * 2441. 与对应负数同时存在的最大正整数
 * 给你一个 不包含 任何零的整数数组 nums ，找出自身与对应的负数都在数组中存在的最大正整数 k 。
 * 返回正整数 k ，如果不存在这样的整数，返回 -1 。
 */
public class Solution {

    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num < 0) {
                set.add(num);
            }
        }
        int res = -1;
        for (int num : nums) {
            if (num > 0 && num > res && set.contains(-num)) {
                res = num;
            }
        }
        return res;
    }

}
