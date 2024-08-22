package com.sean.leetcode.LeetCode3133;

/**
 * @Author xionghaiyang
 * @Date 2024-08-22 19:20
 * @Description https://leetcode.cn/problems/minimum-array-end/
 * 3133. 数组最后一个元素的最小值
 * 给你两个整数 n 和 x 。
 * 你需要构造一个长度为 n 的 正整数 数组 nums ，对于所有 0 <= i < n - 1 ，
 * 满足 nums[i + 1] 大于 nums[i] ，并且数组 nums 中所有元素的按位 AND 运算结果为 x 。
 * 返回 nums[n - 1] 可能的 最小 值。
 */
public class Solution {

    public long minEnd(int n, int x) {
        int bitCount = 128 - Long.numberOfLeadingZeros(n) - Long.numberOfLeadingZeros(x);
        long res = x;
        long m = n - 1;
        int j = 0;
        for (int i = 0; i < bitCount; i++) {
            if (((res >> i) & 1) == 0) {
                if (((m >> j) & 1) == 1) {
                    res |= (1L << i);
                }
                j++;
            }
        }
        return res;
    }

}
