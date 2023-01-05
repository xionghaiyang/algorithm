package com.sean.leetcode.LeetCode1945;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 08:20
 * @Description: https://leetcode.cn/problems/sum-of-digits-of-string-after-convert/
 * 1945. 字符串转化后的各位数字之和
 * 给你一个由小写字母组成的字符串 s ，以及一个整数 k 。
 * 首先，用字母在字母表中的位置替换该字母，将 s 转化 为一个整数（也就是，'a' 用 1 替换，'b' 用 2 替换，... 'z' 用 26 替换）。
 * 接着，将整数 转换 为其 各位数字之和 。共重复 转换 操作 k 次 。
 * 例如，如果 s = "zbax" 且 k = 2 ，那么执行下述步骤后得到的结果是整数 8 ：
 * 转化："zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
 * 转换 #1：262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
 * 转换 #2：17 ➝ 1 + 7 ➝ 8
 * 返回执行上述操作后得到的结果整数。
 */
public class Solution {

    public int getLucky(String s, int k) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += process(s.charAt(i) - 'a' + 1);
        }
        if (k == 1) {
            return res;
        }
        for (int i = 2; i <= k; i++) {
            res = process(res);
        }
        return res;
    }

    private int process(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }

}
