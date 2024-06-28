package com.sean.leetcode.LeetCodeOffer10II;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 20:32
 * @Description: https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
public class Solution {

    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int mod = 1000000007;
        int p = 1;
        int q = 1;
        int r = 2;
        for (int i = 2; i <= n; i++) {
            r = (p + q) % mod;
            p = q;
            q = r;
        }
        return r;
    }

}
