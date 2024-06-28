package com.sean.leetcode.LeetCode2928;

/**
 * @Author xionghaiyang
 * @Date 2024-06-01 08:43
 * @Description https://leetcode.cn/problems/distribute-candies-among-children-i/
 * 2928. 给小朋友们分糖果 I
 * 给你两个正整数 n 和 limit 。
 * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
 */
public class Solution {

    public int distributeCandies(int n, int limit) {
        int res = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                if (i + j > n) {
                    break;
                }
                if (n - i - j <= limit) {
                    res++;
                }
            }
        }
        return res;
    }

    public int distributeCandies1(int n, int limit) {
        int res = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            if (n - i > 2 * limit) {
                continue;
            }
            res += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }
        return res;
    }

    public int distributeCandies2(int n, int limit) {
        return cal(n + 2) - 3 * cal(n - limit + 1) + 3 * cal(n - (limit + 1) * 2 + 2) - cal(n - 3 * (limit + 1) + 2);
    }

    private int cal(int x) {
        if (x < 0) {
            return 0;
        }
        return x * (x - 1) / 2;
    }

}
