package com.sean.leetcode.LeetCode640;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-10 11:33
 * @Description: https://leetcode.cn/problems/solve-the-equation/
 * 640. 求解方程
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。
 * 该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 * 如果方程没有解，请返回 "No solution" 。
 * 如果方程有无限解，则返回 “Infinite solutions” 。
 * 如果方程中只有一个解，要保证返回值 'x' 是一个整数。
 */
public class Solution {

    public String solveEquation(String equation) {
        String[] s = equation.split("=");
        int[] left = process(s[0]);
        int[] right = process(s[1]);
        if (left[0] == right[0]) {
            return left[1] == right[1] ? "Infinite solutions" : "No solution";
        }
        return "x=" + (right[1] - left[1]) / (left[0] - right[0]);
    }

    private int[] process(String s) {
        int[] res = new int[2];
        int sign = 1, sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    sum = sum * 10 + (s.charAt(i) - '0');
                    i++;
                }
                if (i < s.length() && s.charAt(i) == 'x') {
                    res[0] += sign * sum;
                } else {
                    res[1] += sign * sum;
                    i--;
                }
                sum = 0;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else {
                res[0] += sign;
            }
        }
        return res;
    }

}
