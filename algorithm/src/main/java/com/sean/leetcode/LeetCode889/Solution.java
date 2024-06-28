package com.sean.leetcode.LeetCode889;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-22 12:40
 * @Description: https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * 889. 根据前序和后序遍历构造二叉树
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，
 * postorder 是同一棵树的后序遍历，重构并返回二叉树。
 * 如果存在多个答案，您可以返回其中 任何 一个。
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int n = pre.length;
        TreeNode root = new TreeNode(pre[0]);
        if (n == 1) {
            return root;
        }
        int L = 0;
        for (int i = 0; i < n; i++) {
            if (post[i] == pre[1]) {
                L = i + 1;
            }
        }
        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L + 1), Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L + 1, n), Arrays.copyOfRange(post, L, n - 1));
        return root;
    }

    int[] pre;
    int[] post;

    public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0, 0, pre.length);
    }

    private TreeNode make(int i0, int i1, int N) {
        if (N == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) {
            return root;
        }
        int L = 1;
        for (; L < N; L++) {
            if (post[i1 + L - 1] == pre[i0 + 1]) {
                break;
            }
        }
        root.left = make(i0 + 1, i1, L);
        root.right = make(i0 + L + 1, i1 + L, N - 1 - L);
        return root;
    }

}
