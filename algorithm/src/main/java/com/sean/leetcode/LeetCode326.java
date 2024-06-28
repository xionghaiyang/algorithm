package com.sean.leetcode;

/**
 * 3的幂
 * https://leetcode-cn.com/problems/power-of-three/
 */
public class LeetCode326 {

    //循环迭代
    public static boolean isPowerOfThree1(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    //基准转换
    public static boolean isPowerOfThree(int n) {
        return Integer.toString(n ,3).matches("^10*$");
    }

}
