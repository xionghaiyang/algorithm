package com.sean.leetcode.LeetCode955;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-12-21 08:22
 * @Description https://leetcode.cn/problems/delete-columns-to-make-sorted-ii
 * 955. 删列造序 II
 * 给定由 n 个字符串组成的数组 strs，其中每个字符串长度相等。
 * 选取一个删除索引序列，对于 strs 中的每个字符串，删除对应每个索引处的字符。
 * 比如，有 strs = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 strs 为["bef", "vyz"]。
 * 假设，我们选择了一组删除索引 answer，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（strs[0] <= strs[1] <= strs[2] ... <= strs[n - 1]）排列的，然后请你返回 answer.length 的最小可能值。
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 100
 * strs[i] 由小写英文字母组成
 */
public class Solution {

    //O(N * M^2)
    public int minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length();
        String[] s = new String[n];
        Arrays.fill(s, "");
        int res = 0;
        out:
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                if ((s[i] + strs[i].charAt(j)).compareTo(s[i + 1] + strs[i + 1].charAt(j)) > 0) {
                    res++;
                    continue out;
                }
            }
            for (int i = 0; i < n; i++) {
                s[i] += strs[i].charAt(j);
            }
        }
        return res;
    }

    //O(N * M)
    public int minDeletionSize1(String[] strs) {
        int n = strs.length, m = strs[0].length(), size = n - 1;
        int[] checkList = new int[size];
        for (int i = 0; i < size; i++) {
            checkList[i] = i;
        }
        int res = 0;
        out:
        for (int j = 0; j < m; j++) {
            for (int t = 0; t < size; t++) {
                int i = checkList[t];
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    res++;
                    continue out;
                }
            }
            int newSize = 0;
            for (int t = 0; t < size; t++) {
                int i = checkList[t];
                if (strs[i].charAt(j) == strs[i + 1].charAt(j)) {
                    checkList[newSize++] = i;
                }
            }
            size = newSize;
        }
        return res;
    }

}
