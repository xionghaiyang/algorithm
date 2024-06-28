package com.sean.leetcode.LeetCode687;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-02 11:32
 * @Description: https://leetcode.cn/problems/longest-univalue-path/
 * 687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度 由它们之间的边数表示。
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

    int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        process(root);
        return res;
    }

    private int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftInfo = process(root.left);
        int rightInfo = process(root.right);
        int left = 0, right = 0;
        if (root.left != null && root.val == root.left.val) {
            left = leftInfo + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            right = rightInfo + 1;
        }
        res = Math.max(res, left + right);
        return Math.max(left, right);
    }

}
