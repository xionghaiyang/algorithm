package com.sean.leetcode.LeetCode124;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-27 20:15
 * @Description: https://leetcode.cn/problems/binary-tree-maximum-path-sum/?plan=zhijigangwei&plan_progress=stbyva7
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
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
