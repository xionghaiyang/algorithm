package com.sean.leetcode.LeetCode96;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-27 17:42
 * @Description: https://leetcode.cn/problems/unique-binary-search-trees/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 */
public class Solution {

    public int numTrees(int n) {
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

}
