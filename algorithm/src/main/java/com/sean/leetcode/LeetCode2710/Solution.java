package com.sean.leetcode.LeetCode2710;

/**
 * @Author xionghaiyang
 * @Date 2024-06-29 05:44
 * @Description https://leetcode.cn/problems/remove-trailing-zeros-from-a-string/
 * 2710. 移除字符串中的尾随零
 * 给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
 */
public class Solution {

    public String removeTrailingZeros(String num) {
        int lastNonZeroDigit = findLastNonZeroDigit(num);
//        if (lastNonZeroDigit == -1) {
//            return "";
//        }
        return num.substring(0, lastNonZeroDigit);
    }

    private int findLastNonZeroDigit(String num) {
        int n = num.length();
        for (int i = n - 1; i >= 0; i--) {
            if (num.charAt(i) != '0') {
                return i;
            }
        }
        return -1;
    }

    public String removeTrailingZeros1(String num) {
        int len = num.length();
        while (len > 0 && num.charAt(len - 1) == '0') {
            len--;
        }
        return num.substring(0, len);
    }

}
