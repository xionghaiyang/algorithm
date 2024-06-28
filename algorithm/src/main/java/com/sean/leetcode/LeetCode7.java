package com.sean.leetcode;

/**
 * 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class LeetCode7 {

    public static int reverse(int x) {
        int res = 0;
        int last = 0;
        while (x != 0) {
            last = res;
            res = res * 10 + x % 10;
            if (last != res / 10) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(120));
    }
}
