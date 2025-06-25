package com.sean.leetcode.LeetCode6;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-25 20:25
 * @Description https://leetcode.cn/problems/zigzag-conversion
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class Solution {

    public String convert(String s, int numRows) {
        int n = s.length();
        if (numRows == 1 || numRows >= n) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        Arrays.setAll(builders, i -> new StringBuilder());
        for (int i = 0, j = 0, k = 2 * numRows - 2; i < n; i++) {
            builders[j].append(s.charAt(i));
            if (i % k < numRows - 1) {
                j++;
            } else {
                j--;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder builder : builders) {
            res.append(builder);
        }
        return res.toString();
    }

}
