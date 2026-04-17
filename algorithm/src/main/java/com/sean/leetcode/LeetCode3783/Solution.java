package com.sean.leetcode.LeetCode3783;

/**
 * @Author xionghaiyang
 * @Date 2026-04-18 06:25
 * @Description https://leetcode.cn/problems/mirror-distance-of-an-integer
 * 3783. 整数的镜像距离
 * 给你一个整数 n。
 * 定义它的 镜像距离 为：abs(n - reverse(n))​​​​​​​，其中 reverse(n) 表示将 n 的数字反转后形成的整数。
 * 返回表示 n 的镜像距离的整数。
 * 其中，abs(x) 表示 x 的绝对值。
 * 1 <= n <= 10^9
 */
public class Solution {

    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }

    private int reverse(int n) {
        int res = 0;
        while (n > 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }

}
