package com.sean.leetcode.LeetCode2708;

/**
 * @Author xionghaiyang
 * @Date 2024-09-03 09:31
 * @Description https://leetcode.cn/problems/maximum-strength-of-a-group/
 * 2708. 一个小组的最大实力值
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。
 * 老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，
 * 如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，
 * 那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​] 。
 * 请你返回老师创建的小组能得到的最大实力值为多少。
 * 1 <= nums.length <= 13
 * -9 <= nums[i] <= 9
 */
public class Solution {

    public long maxStrength(int[] nums) {
        int negativeCount = 0, zeroCount = 0, positiveCount = 0;
        long prod = 1;
        int maxNegative = -9;
        for (int num : nums) {
            if (num < 0) {
                negativeCount++;
                prod *= num;
                maxNegative = Math.max(maxNegative, num);
            } else if (num == 0) {
                zeroCount++;
            } else {
                positiveCount++;
                prod *= num;
            }
        }
        if (negativeCount == 1 && zeroCount == 0 && positiveCount == 0) {
            return nums[0];
        }
        if (negativeCount <= 1 && positiveCount == 0) {
            return 0;
        }
        if (prod < 0) {
            return prod / maxNegative;
        } else {
            return prod;
        }
    }

    public long maxStrength1(int[] nums) {
        long max = nums[0];
        long min = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            long x = nums[i];
            long temp = min;
            min = Math.min(Math.min(min, x), Math.min(min * x, max * x));
            max = Math.max(Math.max(max, x), Math.max(max * x, temp * x));
        }
        return max;
    }

}
