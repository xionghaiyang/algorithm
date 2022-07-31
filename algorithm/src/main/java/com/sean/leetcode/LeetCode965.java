package com.sean.leetcode;

public class LeetCode965 {


    public static class TreeNode {
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

    public static boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.left.val != root.val || !isUnivalTree(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val != root.val || !isUnivalTree(root.right)) {
                return false;
            }
        }
        return true;
    }

}
