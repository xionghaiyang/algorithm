package com.sean.leetcode.LeetCode67;

/**
 * @Author xionghaiyang
 * @Date 2025-07-23 17:53
 * @Description https://leetcode.cn/problems/add-binary
 * 67. 二进制求和
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * 1 <= a.length, b.length <= 10^4
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 */
public class Solution {

    public String addBinary(String a, String b) {
        int m = a.length(), n = b.length();
        StringBuilder str = new StringBuilder();
        int i = m - 1, j = n - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            carry += (i >= 0 ? a.charAt(i--) - '0' : 0) + (j >= 0 ? b.charAt(j--) - '0' : 0);
            str.append(carry % 2);
            carry >>= 1;
        }
        return str.reverse().toString();
    }

}
