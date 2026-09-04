package com.sean.leetcode.LeetCode43;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-04 10:09
 * @Description: https://leetcode.cn/problems/multiply-strings
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 */
public class Solution {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String res = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder cur = new StringBuilder();
            int carry = 0;
            for (int j = n - 1; j > i; j--) {
                cur.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int t = x * y + carry;
                cur.append(t % 10);
                carry = t / 10;
            }
            if (carry != 0) {
                cur.append(carry % 10);
            }
            res = add(res, cur.reverse().toString());
        }
        return res;
    }

    private String add(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int t = x + y + carry;
            res.append(t % 10);
            carry = t / 10;
            i--;
            j--;
        }
        return res.reverse().toString();
    }

}
