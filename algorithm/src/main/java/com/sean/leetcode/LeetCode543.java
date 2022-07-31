package com.sean.leetcode;

public class LeetCode543 {

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

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        process(root);
        return res;
    }

    private int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = process(root.left);
        int rightDepth = process(root.right);
        res = Math.max(res, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
