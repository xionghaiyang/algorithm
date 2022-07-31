package com.sean.leetcode;

/**
 * https://leetcode-cn.com/problems/sqrtx/
 * Sqrt(x)
 */
public class LeetCode69 {

    int a;

    public int mySqrt(int x) {
        a = x;
        if (x == 0) {
            return 0;
        }
        return (int) sqrts(x);
    }

    public double sqrts(double x) {
        double res = (x + a / x) / 2;
        if (res == x) {
            return x;
        } else {
            return sqrts(res);
        }
    }

}
