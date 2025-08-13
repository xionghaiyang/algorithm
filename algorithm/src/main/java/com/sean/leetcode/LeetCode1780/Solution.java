package com.sean.leetcode.LeetCode1780;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 08:24
 * @Description: https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
 * 对于一个整数 y ，如果存在整数 x 满足 y == 3^x ，我们称这个整数 y 是三的幂。
 * 1 <= n <= 10^7
 */
public class Solution {

    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

}
