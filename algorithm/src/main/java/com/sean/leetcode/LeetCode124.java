package com.sean.leetcode;

import sun.awt.AWTAccessor;
import sun.dc.pr.PRError;

public class LeetCode124 {

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

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root);
        return max;
    }

    private int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(0, process(root.left));
        int rightMax = Math.max(0, process(root.right));
        max = Math.max(max, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }

}
