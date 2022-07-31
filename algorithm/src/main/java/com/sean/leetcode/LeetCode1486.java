package com.sean.leetcode;

/**
 * 数组异或操作
 * https://leetcode-cn.com/problems/xor-operation-in-an-array/
 */
public class LeetCode1486 {

    public static int xorOperation(int n, int start) {
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= 2 * i + start;
        }
        return xor;
    }

    public static void main(String[] args) {
        System.out.println(xorOperation(5,0));
        System.out.println(xorOperation(4,3));
        System.out.println(xorOperation(1,7));
        System.out.println(xorOperation(10,5));
    }

}
