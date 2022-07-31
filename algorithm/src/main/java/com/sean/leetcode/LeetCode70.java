package com.sean.leetcode;

/**
 * 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class LeetCode70 {

    //超出时间限制
    public static int climbStairs1(int n) {
        return process(n);
    }

    private static int process(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return process(n - 1) + process(n - 2);
    }

    public static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p+q;
        }
        return r;
    }

}
