package com.sean.learning01.class06;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class Code04_MaximumDepthOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
