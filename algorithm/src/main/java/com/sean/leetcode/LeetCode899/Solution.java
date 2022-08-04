package com.sean.leetcode.LeetCode899;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-03 11:36
 * @Description: https://leetcode.cn/problems/orderly-queue/
 * 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 */
public class Solution {

    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String res = s;
            StringBuilder sb = new StringBuilder(s);
            int n = s.length();
            for (int i = 1; i < n; i++) {
                char c = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(c);
                if (sb.toString().compareTo(res) < 0) {
                    res = sb.toString();
                }
            }
            return res;
        } else {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }

}
