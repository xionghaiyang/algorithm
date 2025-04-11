package com.sean.leetcode.LeetCode2843;

/**
 * @Author xionghaiyang
 * @Date 2025-04-11 09:09
 * @Description https://leetcode.cn/problems/count-symmetric-integers
 * 2843. 统计对称整数的数目
 * 给你两个正整数 low 和 high 。
 * 对于一个由 2 * n 位数字组成的整数 x ，如果其前 n 位数字之和与后 n 位数字之和相等，则认为这个数字是一个对称整数。
 * 返回在 [low, high] 范围内的 对称整数的数目 。
 * 1 <= low <= high <= 10^4
 */
public class Solution {

    public int countSymmetricIntegers(int low, int high) {
        int res = 0;
        for (int i = low; i <= high; i++) {
            String s = String.valueOf(i);
            if (process(s)) {
                res++;
            }
        }
        return res;
    }

    private boolean process(String s) {
        if ((s.length() & 1) != 0) {
            return false;
        }
        int n = s.length() >> 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (s.charAt(i) - '0');
        }
        for (int i = n; i < 2 * n; i++) {
            sum -= (s.charAt(i) - '0');
        }
        return sum == 0;
    }

    private static final int[][] findDigitsBySum = {
            {0},
            {1, 10},
            {2, 11, 20},
            {3, 12, 21, 30},
            {4, 13, 22, 31, 40},
            {5, 14, 23, 32, 41, 50},
            {6, 15, 24, 33, 42, 51, 60},
            {7, 16, 25, 34, 43, 52, 61, 70},
            {8, 17, 26, 35, 44, 53, 62, 71, 80},
            {9, 18, 27, 36, 45, 54, 63, 72, 81, 90},
            {19, 28, 37, 46, 55, 64, 73, 82, 91},
            {29, 38, 47, 56, 65, 74, 83, 92},
            {39, 48, 57, 66, 75, 84, 93},
            {49, 58, 67, 76, 85, 94},
            {59, 68, 77, 86, 95},
            {69, 78, 87, 96},
            {79, 88, 97},
            {89, 98},
            {99}
    };

    public int countSymmetricIntegers1(int low, int high) {
        int res = 0;
        for (int i = 11; i <= 99; i += 11) {
            if (low <= i && i <= high) {
                res++;
            }
        }
        outer:
        for (int i = 10; i <= 99; i++) {
            int leftSum = i / 10 + i % 10;
            for (int j : findDigitsBySum[leftSum]) {
                j += 100 * i;
                if (j < low) {
                    continue;
                }
                if (j > high) {
                    break outer;
                }
                res++;
            }
        }
        return res;
    }

}
