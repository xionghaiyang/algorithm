package com.sean.leetcode.LeetCode960;

/**
 * @Author xionghaiyang
 * @Date 2025-12-22 12:32
 * @Description https://leetcode.cn/problems/delete-columns-to-make-sorted-iii
 * 960. 删列造序 III
 * 给定由 n 个小写字母字符串组成的数组 strs ，其中每个字符串长度相等。
 * 选取一个删除索引序列，对于 strs 中的每个字符串，删除对应每个索引处的字符。
 * 比如，有 strs = ["abcdef","uvwxyz"] ，删除索引序列 {0, 2, 3} ，删除后为 ["bef", "vyz"] 。
 * 假设，我们选择了一组删除索引 answer ，那么在执行删除操作之后，最终得到的数组的行中的 每个元素 都是按字典序排列的（即 (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]) 和 (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]) ，依此类推）。
 * 请返回 answer.length 的最小可能值 。
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 100
 * strs[i] 由小写英文字母组成
 */
public class Solution {

    public int minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length();
        int[] f = new int[m];
        int maxF = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] > f[i] && check(strs, j, i)) {
                    f[i] = f[j];
                }
            }
            f[i]++;
            maxF = Math.max(maxF, f[i]);
        }
        return m - maxF;
    }

    private boolean check(String[] strs, int j, int i) {
        for (String s : strs) {
            if (s.charAt(j) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
