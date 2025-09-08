package com.sean.leetcode.LeetCode783;

/**
 * @Author xionghaiyang
 * @Date 2025-09-08 20:49
 * @Description https://leetcode.cn/problems/minimum-distance-between-bst-nodes
 * 783. 二叉搜索树节点最小距离
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 树中节点的数目范围是 [2, 100]
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

    public class Info {
        private int max;
        private int min;
        private int minDiff;

        public Info(int max, int min, int minDiff) {
            this.max = max;
            this.min = min;
            this.minDiff = minDiff;
        }
    }

    public int minDiffInBST(TreeNode root) {
        return process(root).minDiff;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        int max = root.val, min = root.val, minDiff = Integer.MAX_VALUE;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if (leftInfo != null) {
            //max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            minDiff = Math.min(minDiff, leftInfo.minDiff);
            minDiff = Math.min(minDiff, root.val - leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            //min = Math.min(min, rightInfo.min);
            minDiff = Math.min(minDiff, rightInfo.minDiff);
            minDiff = Math.min(minDiff, rightInfo.min - root.val);
        }
        return new Info(max, min, minDiff);
    }

}
