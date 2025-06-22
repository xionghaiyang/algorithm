package com.sean.leetcode.LeetCode530;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-13 21:54
 * @Description: https://leetcode.cn/problems/minimum-absolute-difference-in-bst
 * 530. 二叉搜索树的最小绝对差
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 树中节点的数目范围是 [2, 10^4]
 * 0 <= Node.val <= 10^5
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

    public int getMinimumDifference(TreeNode root) {
        return process(root).minDiff;
    }

    public class Info {
        public int min;
        public int max;
        public int minDiff;

        public Info(int min, int max, int minDiff) {
            this.min = min;
            this.max = max;
            this.minDiff = minDiff;
        }
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        int min = root.val, max = root.val, minDiff = Integer.MAX_VALUE;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            minDiff = Math.min(minDiff, leftInfo.minDiff);
            minDiff = Math.min(minDiff, root.val - leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            minDiff = Math.min(minDiff, rightInfo.minDiff);
            minDiff = Math.min(minDiff, rightInfo.min - root.val);
        }
        return new Info(min, max, minDiff);
    }

}
