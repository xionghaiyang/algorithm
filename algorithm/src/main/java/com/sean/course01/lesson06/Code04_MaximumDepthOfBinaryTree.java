package com.sean.course01.lesson06;

/**
 * @Author xionghaiyang
 * @Date 2025-03-24 20:51
 * @Description https://leetcode.cn/problems/maximum-depth-of-binary-tree
 * 返回一棵树的最大深度
 */
public class Code04_MaximumDepthOfBinaryTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
