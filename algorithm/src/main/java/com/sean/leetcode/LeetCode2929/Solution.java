package com.sean.leetcode.LeetCode2929;

/**
 * @Author xionghaiyang
 * @Date 2025-06-01 07:11
 * @Description https://leetcode.cn/problems/distribute-candies-among-children-ii
 * 2929. 给小朋友们分糖果 II
 * 给你两个正整数 n 和 limit 。
 * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
 * 1 <= n <= 10^6
 * 1 <= limit <= 10^6
 */
public class Solution {

    public long distributeCandies(int n, int limit) {
        return cal(n + 2) - 3 * cal(n - limit + 1) + 3 * cal(n - 2 * limit) - cal(n - 3 * limit - 1);
    }

    private long cal(int x) {
        if (x < 0) {
            return 0;
        }
        return (long) x * (x - 1) / 2;
    }

}
