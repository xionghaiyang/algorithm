package com.sean.leetcode.LeetCode944;

/**
 * @Author xionghaiyang
 * @Date 2025-12-20 10:06
 * @Description https://leetcode.cn/problems/delete-columns-to-make-sorted
 * 944. 删列造序
 * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * 这些字符串可以每个一行，排成一个网格。
 * 例如，strs = ["abc", "bce", "cae"] 可以排列为：
 * abc
 * bce
 * cae
 * 你需要找出并删除 不是按字典序非严格递增排列的 列。
 * 在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按字典序非严格递增排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * 返回你需要删除的列数。
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 1000
 * strs[i] 由小写英文字母组成
 */
public class Solution {

    public int minDeletionSize(String[] strs) {
        int res = 0;
        int m = strs.length, n = strs[0].length();
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                if (strs[i - 1].charAt(j) > strs[i].charAt(j)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }

}
