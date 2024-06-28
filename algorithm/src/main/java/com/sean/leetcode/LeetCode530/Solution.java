package com.sean.leetcode.LeetCode530;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-13 21:54
 * @Description: https://leetcode.cn/problems/minimum-absolute-difference-in-bst/?envType=study-plan-v2&envId=top-interview-150
 * 530. 二叉搜索树的最小绝对差
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
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

    private int res;
    private int pre;

    public int getMinimumDifference(TreeNode root) {
        res = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            res = Math.min(res, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }

}
