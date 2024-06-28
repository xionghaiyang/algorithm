package com.sean.leetcode.LeetCode2357;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-24 08:14
 * @Description: https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts/
 * 2357. 使数组中所有元素都等于零
 * 给你一个非负整数数组 nums 。在一步操作中，你必须：
 * 选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。
 * nums 中的每个正整数都减去 x。
 * 返回使 nums 中所有元素都等于 0 需要的 最少 操作数。
 */
public class Solution {

    public int minimumOperations1(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int cur = 0;
        for (int num : nums) {
            if (cur < num) {
                cur = num;
                res++;
            }
        }
        return res;
    }

    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }

}
