package com.sean.leetcode.LeetCode441;

/**
 * @Author xionghaiyang
 * @Date 2026-04-09 19:43
 * @Description https://leetcode.cn/problems/arranging-coins
 * 441. 排列硬币
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。
 * 对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。
 * 阶梯的最后一行 可能 是不完整的。
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 * 1 <= n <= 2^31 - 1
 */
public class Solution {

    public int arrangeCoins(int n) {
        int res = 0;
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long coins = (long) mid * (mid + 1) / 2;
            if (coins == n) {
                res = mid;
                break;
            } else if (coins < n) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

}
