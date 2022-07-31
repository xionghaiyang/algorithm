package com.sean.leetcode;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/27 16:17
 */
public class LeetCode96 {

    /**
     * https://leetcode.cn/problems/unique-binary-search-trees/
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */

    public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] += f[j - 1] * f[i - j];
            }
        }
        return f[n];
    }

}
