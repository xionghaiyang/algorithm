package com.sean.leetcode.LeetCodeInterview0404;

/**
 * @Author xionghaiyang
 * @Date 2025-10-21 19:01
 * @Description https://leetcode.cn/problems/check-balance-lcci
 * 面试题 04.04. 检查平衡性
 * 实现一个函数，检查二叉树是否平衡。
 * 在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
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

    public class Info {
        private int height;
        private boolean isBalance;

        public Info(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return dfs(root).isBalance;
    }

    private Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        return new Info(height, isBalance);
    }

}
