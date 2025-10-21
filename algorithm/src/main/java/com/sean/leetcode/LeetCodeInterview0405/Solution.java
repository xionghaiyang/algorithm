package com.sean.leetcode.LeetCodeInterview0405;

/**
 * @Author xionghaiyang
 * @Date 2025-10-21 19:29
 * @Description https://leetcode.cn/problems/legal-binary-search-tree-lcci
 * 面试题 04.05. 合法二叉搜索树
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
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

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        long x = node.val;
        return left < x && x < right && isValidBST(node.left, left, x) && isValidBST(node.right, x, right);
    }

    private long pre = Long.MIN_VALUE;

    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST1(root.left) || root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST1(root.right);
    }

    public boolean isValidBST2(TreeNode root) {
        return dfs(root)[1] != Long.MAX_VALUE;
    }

    private long[] dfs(TreeNode node) {
        if (node == null) {
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        }
        long[] left = dfs(node.left);
        long x = node.val;
        if (x <= left[1]) {
            return new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
        }
        long[] right = dfs(node.right);
        if (x >= right[0]) {
            return new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
        }
        return new long[]{Math.min(left[0], x), Math.max(right[1], x)};
    }

}
