package com.sean.leetcode;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/3/28 9:21
 * @Description: https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 * 交替位二进制数
 * @version: 1.0
 */
public class LeetCode693 {

    public boolean hasAlternatingBits(int n) {
        int prev = 2;
        while (n != 0) {
            int cur = n % 2;
            if (cur == prev) {
                return false;
            }
            prev = cur;
            n /= 2;
        }
        return true;
    }

}
