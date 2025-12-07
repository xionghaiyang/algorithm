package com.sean.leetcode.LeetCode1523;

/**
 * @Author xionghaiyang
 * @Date 2025-12-07 11:17
 * @Description https://leetcode.cn/problems/count-odd-numbers-in-an-interval-range
 * 1523. 在区间范围内统计奇数数目
 * 给你两个非负整数 low 和 high 。
 * 请你返回 low 和 high 之间（包括二者）奇数的数目。
 * 0 <= low <= high <= 10^9
 */
public class Solution {

    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }

}
