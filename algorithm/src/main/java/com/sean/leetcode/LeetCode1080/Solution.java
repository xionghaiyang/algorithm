package com.sean.leetcode.LeetCode1080;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-22 11:05
 * @Description: https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/
 * 1080. 根到叶路径上的不足节点
 * 给你二叉树的根节点 root 和一个整数 limit ，
 * 请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，
 * 则该节点被称之为 不足节点 ，需要被删除。
 * 叶子节点，就是没有子节点的节点。
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

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean flag = process(root, 0, limit);
        return flag ? root : null;
    }

    private boolean process(TreeNode node, int sum, int limit) {
        //如果节点为null，则为不足节点
        if (node == null) {
            return false;
        }
        //如果节点是叶节点，判断是否为不足节点
        if (node.left == null && node.right == null) {
            return node.val + sum >= limit;
        }
        boolean leftFlag = process(node.left, sum + node.val, limit);
        boolean rightFlag = process(node.right, sum + node.val, limit);
        if (!leftFlag) {
            node.left = null;
        }
        if (!rightFlag) {
            node.right = null;
        }
        return leftFlag || rightFlag;
    }

}
