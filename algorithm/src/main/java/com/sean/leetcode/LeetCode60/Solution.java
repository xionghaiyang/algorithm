package com.sean.leetcode.LeetCode60;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-15 15:26
 * @Description: https://leetcode.cn/problems/permutation-sequence/
 * 60. 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 */
public class Solution {

    private int n;
    private int k;
    private boolean[] used;
    private int[] factorial;

    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        used = new boolean[n + 1];
        Arrays.fill(used, false);
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }

    //在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }
        //计算还未确定的全排列的个数，第一个进入的时候是n-1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            return;
        }
    }

}
