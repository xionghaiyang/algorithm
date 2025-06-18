package com.sean.leetcode.LeetCode763;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-18 21:47
 * @Description https://leetcode.cn/problems/partition-labels
 * 763. 划分字母区间
 * 给你一个字符串 s 。
 * 我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 */
public class Solution {

    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0, left = 0, right = -1; i < n; i++) {
            right = Math.max(right, arr[s.charAt(i) - 'a']);
            if (i == right) {
                res.add(right - left + 1);
                left = right + 1;
            }
        }
        return res;
    }

}
