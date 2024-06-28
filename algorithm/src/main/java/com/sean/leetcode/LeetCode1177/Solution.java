package com.sean.leetcode.LeetCode1177;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-15 08:42
 * @Description: https://leetcode.cn/problems/can-make-palindrome-from-substring/
 * 1177. 构建回文串检测
 * 给你一个字符串 s，请你对 s 的子串进行检测。
 * 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。
 * 我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。 
 * 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
 * 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
 * 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，
 * 也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换其中的两个字母。
 * （另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）
 */
public class Solution {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            count[i + 1] = count[i] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            int k = query[2];
            int bits = 0;
            int x = count[right + 1] ^ count[left];
            while (x > 0) {
                x &= x - 1;
                bits++;
            }
            res.add(bits <= 2 * k + 1);
        }
        return res;
    }

}
