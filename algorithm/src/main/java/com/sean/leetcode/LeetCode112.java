package com.sean.leetcode;

public class LeetCode112 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isSum = false;

    public static boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        isSum = false;
        process(root, 0, targetSum);
        return isSum;
    }

    public static void process(TreeNode x, int preSum, int sum) {
        if (x.left == null && x.right == null) {
            if (x.val + preSum == sum) {
                isSum = true;
            }
            return;
        }
        preSum += x.val;
        if (x.left != null) {
            process(x.left, preSum, sum);
        }
        if (x.right != null) {
            process(x.right, preSum, sum);
        }
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process1(root, targetSum);
    }

    public static boolean process1(TreeNode root, int rest) {
        if (root.left == null && root.right == null) {
            return root.val == rest;
        }
        boolean ans = root.left != null ? process1(root.left, rest - root.val) : false;
        ans |= root.right != null ? process1(root.right, rest - root.val) : false;
        return ans;
    }

}
