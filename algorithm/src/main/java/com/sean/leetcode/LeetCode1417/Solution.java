package com.sean.leetcode.LeetCode1417;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-11 11:32
 * @Description: https://leetcode.cn/problems/reformat-the-string/
 * 1417. 重新格式化字符串
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。
 * 也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 */
public class Solution {

    public String reformat(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int digitSum = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(chars[i])) {
                digitSum++;
            }
        }
        int alphaSum = n - digitSum;
        if (Math.abs(digitSum - alphaSum) > 1) {
            return "";
        }
        boolean flag = digitSum > alphaSum;
        char[] res = new char[n];
        int i = 0, j = 0;
        if (digitSum >= alphaSum) {
            j++;
        } else {
            i++;
        }
        for (int k = 0; k < n; k++) {
            if (Character.isDigit(chars[k])) {
                res[i] = chars[k];
                i += 2;
            } else {
                res[j] = chars[k];
                j += 2;
            }
        }
        return new String(res);
    }

}
